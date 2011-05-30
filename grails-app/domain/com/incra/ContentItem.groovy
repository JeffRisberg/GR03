package com.incra

import com.incra.domain.AbstractDomain
import com.incra.domain.ContentItemType

/**
 * The <i>ContentItem</i> entity has a name and a type, and also an associated set of tags.
 * This was created by merging the ContentItem, Vendor, and Partner classes.
 * 
 * @author Jeff Risberg
 * @since 03/05/11
 */
class ContentItem extends AbstractDomain {
	String name
	String description
	ContentItemType contentItemType
	String url
	byte[] photo
	boolean partnerFlag = false
	boolean paidFlag = false
	boolean approvedFlag = false

	static hasMany = [ contentSections : ContentSection ]

	static constraints = {
		name(blank: false, unique: true)
		description(maxSize: 500, nullable: true)
		contentItemType()
		photo(nullable: true, maxSize: 1000000)
		url(maxSize: 500, nullable: true)
		partnerFlag()
		paidFlag()
		approvedFlag()
		dateCreated(display: false)
	}

	String toString() {
		name
	}
}
