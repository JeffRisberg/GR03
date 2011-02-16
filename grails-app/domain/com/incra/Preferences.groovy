package com.incra

/**
 * The <i>Preferences</i> entity holds settings that drive the calculations for a given user.
 *
 * @author Jeffrey Risberg
 * @since 10/19/10
 */
class Preferences {
	
	double discountRate = 5.0
	int planningHorizon = 5
	GeoScale geoScale
	TimeScale timeScale
	
	static constraints = {
		discountRate(min: 0.0d, max: 50.0d)
		planningHorizon(min: 1, max: 20)
		geoScale()
		timeScale()
	}
	
	String toString() {
		return "Prefs[${planningHorizon},${geoScale.name},${timeScale.name}}]"
	}
}
