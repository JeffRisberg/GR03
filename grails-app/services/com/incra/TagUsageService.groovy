package com.incra
import java.util.Map;

/**
 * The <i>TagUsageService</i> provides business logic to maintain tag usage, and 
 * the underlying tag pool.
 *
 * @author Spoorthy Ananthaiah, Jeffrey Risberg
 * @since 11/22/10
 */
class TagUsageService {
	
	/**
	 * Return a map of the tags and their counts for a specified entityType. Used 
	 * for generating a tag cloud.
	 */
	Map<String, Integer> getTagCounts(EntityType entityType) {
		Map<String, Integer> tagCountsMap = [:]
		
		TagUsage.createCriteria().list {
			createAlias("tag", "t")
			eq('entityType', entityType)
			projections {
				groupProperty("t.name")
				count("t.id")
			}
		}.each {
			def (tagName, count) = it
			tagCountsMap[tagName] = tagCountsMap[tagName] ? (tagCountsMap[tagName] + count) : count
		}
		
		// Sort the results by count (map key)
		Comparator comparator = [compare: {a, b ->
				b.compareTo(a)
			}] as Comparator
		
		Map sortedMap = new TreeMap(comparator)
		sortedMap.putAll(tagCountsMap)
		
		sortedMap
	}
	
	/**
	 * Return a list of the distinct tags for a given entityType.  Called from the 
	 * controllers to populate tag lists for filtering on a specific entityType.
	 */
	List<Tag> getDistinctTags(EntityType entityType) {
		List<Tag> result = new ArrayList<Tag>();
		
		List<TagUsage> tagUsages = getTagUsages(entityType)
		
		for (TagUsage tagUsage : tagUsages) {
			Tag tag = tagUsage.tag;
			if (result.contains(tag) == false)
				result.add(tag);
		}
		
		result
	}
	
	/**
	 * Return a list of the distinct tags for a given (entityType, entityId) pair.  Called
	 * by the controllers for on show screens.
	 */
	List<Tag> getDistinctTags(EntityType entityType, Long entityId) {
		List<Tag> result = new ArrayList<Tag>();
		
		List<TagUsage> tagUsages = getTagUsages(entityType, entityId);
		
		for (TagUsage tagUsage : tagUsages) {
			Tag tag = tagUsage.tag;
			if (result.contains(tag) == false)
				result.add(tag);
		}
		
		result
	}
	
	/**
	 * Generate a formatted list of tags, using an ellipsis after a specified max length.
	 * Called by the controllers on show screens.
	 */
	String getFormattedTags(List<Tag> tags, int maxLength) {
		StringBuffer sb = new StringBuffer();
		boolean bAtFirst = true;
		
		for (Tag tag : tags) {
			String name = tag.name;
			int length = name.length();
			
			if (sb.length() + length > maxLength) {
				sb.append(", ...")
				break;
			}
			
			if (bAtFirst == false) {
				sb.append(", ")
			}
			sb.append(tag.name)
			bAtFirst = false;
		}
		
		sb.toString();
	}
	
	/**
	 * Save a list of unique tags corresponding tagUsage in database corresponding to 
	 * formatted Tags for a given (entityType, entityId) pair.  Called from the controllers
	 * when a record is being saved or updated.
	 */
	void saveTags(EntityType entityType, Long entityId, String formattedTags) {
		// Create a list of the specified tag names by parsing and collecting
		List<String> givenTagNames = formattedTags.tokenize(",").collect { 
			it.trim().replace(' ', '-')
		}
		Map<String, String> givenTagNamesMap = new HashMap<String, String>()
		for (String givenTagName : givenTagNames) {
			givenTagNamesMap.put(givenTagName, givenTagName)
		}
		
		// Load the prior tag usages for this record to provide a baseline
		List<TagUsage> priorTagUsages = getTagUsages(entityType, entityId)
		Map<String, Tag> priorTagsMap = new HashMap<String, Tag>()
		for (TagUsage tagUsage : priorTagUsages) {
			Tag tag = tagUsage.getTag()
			priorTagsMap.put(tag.name, tag)
		}
		
		// Load all tags from the pool and construct a map
		List<Tag> allTags = Tag.findAll()
		Map<String, Tag> allTagsMap = new HashMap<String, Tag>()
		for (Tag tag : allTags) {
			allTagsMap.put(tag.name, tag)
		}
		
		// Phase1: Verify that each given tag name is assigned.  Add and create if needed
		for (String tagName : givenTagNames) {
			Tag existingTag = allTagsMap.get(tagName)
			
			if (existingTag) {
				if (priorTagsMap.get(tagName) == null) {
					TagUsage tagUsage = new TagUsage
							(tag: existingTag, entityType: entityType, entityId: entityId)
					tagUsage.save()
				}
			}
			else {
				Tag newTag = new Tag(name: tagName)
				newTag.save()
				
				TagUsage tagUsage = new TagUsage
						(tag: newTag, entityType: entityType, entityId: entityId)
				tagUsage.save()
			}
		}
		
		// Phase 2: Verify that all prior tag names that are no longer given to
		// be assigned are removed 
		List<Tag> affectedTags = new ArrayList<Tag>()
		
		for (TagUsage priorTagUsage : priorTagUsages) {
			Tag priorTag = priorTagUsage.getTag()
			String priorTagName = priorTag.name
			
			if (givenTagNames.contains(priorTagName) == false) {
				affectedTags.add(priorTagUsage.tag)
				priorTagUsage.delete(flush: true)
			}
		}
		
		checkAffectedTags(affectedTags)
	}
	
	/**
	 * Delete all tagUsages for a given (entityType, entityId) pair, removing tags
	 * that are no longer referred to (by use of "all-delete-orphan" cascading).  
	 * Called from the controllers when a record is being deleted.
	 */
	void deleteTags(EntityType entityType, Long entityId) {
		List<TagUsage> tagUsages = getTagUsages(entityType, entityId)
		List<Tag> affectedTags = new ArrayList<Tag>()
		
		for (TagUsage tagUsage : tagUsages) {
			affectedTags.add(tagUsage.tag)
			tagUsage.delete(flush: true)
		}
		
		checkAffectedTags(affectedTags)
	}
	
	/**
	 * Get all tagUsage records for a specific (entityType, entityId) pair.  Support
	 * routine.
	 */
	List<TagUsage> getTagUsages(EntityType entityType, Long entityId) {
		TagUsage.findAllByEntityTypeAndEntityId(entityType, entityId)
	}
	
	/**
	 * Get all tagUsage records for a specific entityType.  Support routine.
	 */
	List<TagUsage> getTagUsages(EntityType entityType) {
		TagUsage.findAllByEntityType(entityType)
	}
	
	
	 void addTag(String entityTypeName, Long entityId, String tagName) {
		EntityType entityType = EntityType.findByName(entityTypeName);
		if (entityType) {
			// find/create the tag
			Tag tag = Tag.findByName(tagName)
			
			if (tag == null) {
				tag = new Tag(name: tagName)
				tag.save();
				println "Created tag " + tagName
			}
			
			TagUsage tagUsage = new TagUsage(entityType: entityType, entityId: entityId, tag: tag)
			tagUsage.save();
		}
		else {
			println "Could not find entityType " + entityTypeName
		}
	}
	
	/**
	 * Support routine which checks a given list of tags to see if they are no longer
	 * references and hence should be removed.
	 */
	private void checkAffectedTags(List<Tag> affectedTags) {
		for (Tag affectedTag : affectedTags) {
			List<TagUsage> usages = TagUsage.findAllByTag(affectedTag)
			
			if (usages.size() == 0) {
				affectedTag.delete()
			}
		}
	}
	
	
	
}
