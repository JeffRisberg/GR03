package com.incra

/**
 * Request Map domain class.
 */
class RequestMap {

	String url
	String configAttribute

	static constraints = {
		url(blank: false, unique: true)
		configAttribute(blank: false)
	}
}
