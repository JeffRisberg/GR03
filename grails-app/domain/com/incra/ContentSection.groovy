package com.incra

import com.incra.domain.AbstractDomain

/**
 * The <i>ContentSection</i> entity is a piece of a ContentItem.  The ContentSection
 * typically is used to describe a business unit or some other part of a ContentItem.
 * The display and edit screens for a ContentItem allow for creating and deleting
 * the ContentSections.
 *
 * @author Spoorthy, Jeff
 * @since 03/29/11
 */
class ContentSection extends AbstractDomain {

	String name
	String description
	Contact contact
	Address address
	String website

	static constraints = {
		name(nullable:true, maxSize: 128)
		description(nullable:true, maxSize: 300)
		contact(nullable: true)
		address(nullable: true)
		website(nullable: true, maxSize: 80)
	}

	static belongsTo = [ contentItem : ContentItem ]

	String toString() {
		name
	}
}
