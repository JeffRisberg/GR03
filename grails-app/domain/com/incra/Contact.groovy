package com.incra

/** 
 * The <i>Contact</i> class holds name, phone, email information.
 * 
 * @author Spoorthy Ananthaiah 
 * @since 11/08/10
 */
class Contact {
	
	String contactName
	String phoneNo
	String email
	
	static constraints = {
		contactName(nullable: true, blank: true)
		phoneNo(matches: "\\d{3}\\-\\d{3}\\-\\d{4}")
		email(nullable: true, blank: true, email: true)
	}
	
	String toString() {  
		return "$contactName\n $phoneNo\n $email\n"
	}
}
