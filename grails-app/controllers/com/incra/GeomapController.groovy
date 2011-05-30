package com.incra

import com.incra.controller.SecureController
import com.incra.domain.LogEntrySeverity
import com.incra.pojo.GeomapOverlayPojo
import com.incra.session.GeomapSession

/**
 * The <i>GeomapController</i> class implements operations to draw a Google Maps map.
 *
 * @author Jeffrey Risberg
 * @since 10/09/10
 */
class GeomapController extends SecureController {
	PageFrameworkService pageFrameworkService
	GeomapService geomapService

	static loggingInstructions = [
		index: [key: "Geomap Display", severity: LogEntrySeverity.MEDIUM],
	]

	/** 
	 * Primary display
	 */
	def index = {
		GeomapSession gSession = geomapService.getGeomapSession(session)

		User currentUser = pageFrameworkService.getCurrentUser(session)

		Preferences prefs = Preferences.get(1)

		EntityType etAccount = EntityType.findByName("Account")
		AccountType atCustomer = AccountType.findByName("Customer")

		// construct the overlays
		List<GeomapOverlayPojo> overlays = new ArrayList<GeomapOverlayPojo>()
		GeomapOverlayPojo overlay
		List<Object> records
		def criteria
		def query

		// The pipeline overlay
		overlay = new GeomapOverlayPojo("SBWR Pipeline",
				"<b>Silicon Valley Recycled Water Systems</b> deliver over 10 million " +
				"gallons of recycled water daily through over 100 miles of dedicated pipeline.",
				"http://theRisbergFamily.com/Pipeline.kml", null, null)
		overlay.shown = gSession.isShown("SBWR Pipeline")

		overlays.add(overlay)

		// The customers overlay
		overlay = new GeomapOverlayPojo("SBWR Customers", "Current customers of SBWR", null, "red", etAccount)
		overlay.shown = gSession.isShown("SBWR Customers")

		criteria = Account.createCriteria()
		query = {
			and {				 eq('type', atCustomer) }
		}

		records = criteria.list(params, query)
		overlay.addRecords(records)
		overlays.add(overlay)

		// The facilities overlay
		overlay = new GeomapOverlayPojo("Facilities", "My facilities", null, "green", etAccount)
		overlay.shown = gSession.isShown("Facilities")

		criteria = Account.createCriteria()
		query = {
			and {
				if (currentUser != null) {
					or {
						isNull('user')
						eq('user', currentUser)
					}
				}
				if (currentUser == null) {
					isNull('user')
				}
				ne('type', atCustomer)
			}
		}

		records = criteria.list(params, query)
		overlay.addRecords(records)
		overlays.add(overlay)

		// The projects overlay
		// to be filled in

		// The vendors overlay
		// to be filled in

		// The partners overlay
		// to be filled in

		[overlays: overlays, geoScale: prefs.geoScale,
					centerLat: gSession.centerLat, centerLng: gSession.centerLng,
					zoom: gSession.zoom, mapTypeId: gSession.mapTypeId]
	}

	/**
	 * Handle a posting from the overlay selector form.
	 */
	def changeOverlays = {
		GeomapSession gSession = geomapService.getGeomapSession(session)

		if (params["SBWR Pipeline"])
			gSession.setShown("SBWR Pipeline", true)
		else
			gSession.setShown("SBWR Pipeline", false)

		if (params["SBWR Customers"])
			gSession.setShown("SBWR Customers", true)
		else
			gSession.setShown("SBWR Customers", false)

		if (params["Facilities"])
			gSession.setShown("Facilities", true)
		else
			gSession.setShown("Facilities", false)

		redirect(action: "index")
	}

	/**
	 * Handle an update of the visibility controls
	 */
	def post = {
		// process parameters and store in session
		GeomapSession gSession = geomapService.getGeomapSession(session);

		gSession.showPipeline = (params.showPipeline != null)
		gSession.showCustomers = (params.showCustomers != null)

		// redirect
		redirect(action: "index")
	}

	/**
	 * Invoked via AJAX to inform the server of a change in client map center or zoom.
	 */
	def handleChangeCenterOrZoom = {
		GeomapSession gSession = geomapService.getGeomapSession(session);

		Double centerLat = params.centerLat as Double;
		Double centerLng = params.centerLng as Double;
		Integer zoom = params.zoom as Integer;
		String mapTypeId = params.mapTypeId;

		if (centerLat)
			gSession.centerLat = centerLat;
		if (centerLng)
			gSession.centerLng = centerLng;
		if (zoom)
			gSession.zoom = zoom;
		if (mapTypeId)
			gSession.mapTypeId = mapTypeId;
	}

	/** 
	 * Invoked via AJAX to add a new point on the map
	 */
	def addPoint = {
		println "addPoint " + params
		Double dlatitude = params.latitude as Double
		Double dlongitude = params.longitude as Double

		AccountType atCustomer = AccountType.get(9)

		geomapService.createAccount(session, (String) params.name, atCustomer,
				(String) params.addressLine1, (String) params.addressLine2, (String)
				params.addressLine3, (String) params.city, (String) params.state,
				(String) params.postalCode, dlatitude, dlongitude)

		// generate a JSON response
		render(contentType: 'text/json') {
			success = true
			data = {
				name = params.name
				latitude = dlatitude
				longitude = dlongitude
			}
		}
	}
}
