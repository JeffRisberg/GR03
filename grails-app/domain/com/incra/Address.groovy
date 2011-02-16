package com.incra

/**
 * The <i>Address</i> entity records all fields of a address, including postal code.  It has a verification
 * check for valid stateCode.  It also holds geographic information.
 *
 * @author Spoorthy Ananthaiah
 * @since 11/07/10
 */
class Address {
	
	String addressLine1
	String addressLine2
	String addressLine3
	String city
	String stateCode
	String postalCode
	double latitude = 37.421729
	double longitude = -122.169841
	double elevation = 10.0
	
	static constraints = {
		
		addressLine1(nullable: true)
		addressLine2(nullable: true)
		addressLine3(nullable: true)
		city(nullable: true)
		stateCode(nullable: true, inList: STATES)	
		postalCode(nullable: true, matches: /(\d{5}(-\d{4})?)?/) // Must look like NNNNN or NNNNN-NNNN
	}
	
	static final def STATES = [
		'',
		'AL',
		'AK',
		'AS',
		'AZ',
		'AR',
		'CA',
		'CO',
		'CT',
		'DE',
		'DC',
		'FM',
		'FL',
		'GA',
		'GU',
		'HI',
		'ID',
		'IL',
		'IN',
		'IA',
		'KS',
		'KY',
		'LA',
		'ME',
		'MH',
		'MD',
		'MA',
		'MI',
		'MN',
		'MS',
		'MO',
		'MT',
		'NE',
		'NV',
		'NH',
		'NJ',
		'NM',
		'NY',
		'NC',
		'ND',
		'MP',
		'OH',
		'OK',
		'OR',
		'PW',
		'PA',
		'PR',
		'RI',
		'SC',
		'SD',
		'TN',
		'TX',
		'UT',
		'VT',
		'VI',
		'VA',
		'WA',
		'WV',
		'WI',
		'WY'
	]
	
	String toString() { 
		return "$city, $stateCode $postalCode"
	}
}
