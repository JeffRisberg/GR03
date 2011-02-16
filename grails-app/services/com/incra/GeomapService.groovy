package com.incra

import com.incra.session.GeomapSession;

/**
 * The <i>GeomapService</i> class...
 * 
 * @author Jeffrey Risberg
 * @since 11/14/10
 */
class GeomapService {
	
	/**
	 * Fetch the session, creating if needed.
	 */
	GeomapSession getGeomapSession(def session) {
		GeomapSession gSession = (GeomapSession) session.gSession;
		
		if (gSession == null) {
			Preferences preferences = Preferences.get(1);
			gSession = new GeomapSession(preferences);
			session.gSession = gSession;
		}
		return gSession
	}
	
	/** 
	 * Create a new account 
	 */
	Account createAccount(def session, String name, AccountType accountType, 
	String addressLine1, String addressLine2, String addressLine3, String city, String state, String postalCode,
	Double latitude, Double longitude) {
		GeomapSession gSession = getGeomapSession(session)
		
		Address address = new Address(addressline1: addressLine1, addressLine2: addressLine2, addressLine3: addressLine3,
				city: city, state: state, postalCode: postalCode,
				latitude: latitude, longitude: longitude,
				elevation: 6);
		address.save();
		Account result = new Account(name: name, type: accountType, address: address,				
				inBaseline: true, geoScale: gSession.geoScale)
		result.save();
		
		result;
	}
}
