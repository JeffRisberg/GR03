<html>
	<head>
		<title>Geomap</title>
		<meta name="layout" content="main" />
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<style type="text/css" media="screen">
  #map_canvas { float: left; width: 755px; height: 400px; } 
  #list { float: left; width: 175px; background: #eee; list-style: none; padding: 0px; } 
  #list li { padding: 3px 10px; } 
  #list li:hover { background: #555; color: #fff; cursor: pointer; cursor: hand; }  
  #error { color: red; font-weight: bold; }
</style>
	</head>
	<body>
		<script type="text/javascript">
		  var map;
		  var infoWindow;
		  var geocoder;
		  var bounds;
		  
			function mapInitialize() {			
			var mapCenter = new google.maps.LatLng(${centerLat}, ${centerLng});

			var mapOptions = {
			zoom: ${zoom},
			center: mapCenter,
			mapTypeId: "${mapTypeId}"
			};

			var mapCanvas = $('map_canvas');
			map = new google.maps.Map(mapCanvas, mapOptions);

			google.maps.event.addListener(map, 'idle', function(event) { handleChangeCenterOrZoom(event); });
			google.maps.event.addListener(map, 'maptypeid_changed', function(event) { handleChangeCenterOrZoom(event); });
	
      infowindow = new google.maps.InfoWindow();

      geocoder = new google.maps.Geocoder(); 

		  bounds = new google.maps.LatLngBounds();
		
		  <g:each in="${overlays}" var="overlay">
			  <g:if test="${overlay.shown && overlay.kmlFileUrl}">
			    {
				  var kmlLayerOptions = {
				    preserveViewport: true
				  };

				  var pipeLineLayer = new
				  google.maps.KmlLayer('${overlay.kmlFileUrl}',
				  kmlLayerOptions);
				  pipeLineLayer.setMap(map);
				  }
        </g:if>
      
        <g:if test="${overlay.shown && overlay.kmlFileUrl == null}">
          {
			    var markers = [];
			    <g:each in="${overlay.records}" var="record">
				    var marker = new google.maps.Marker({
				    position: new google.maps.LatLng(${record.address.latitude}, ${record.address.longitude}),
				    title: '${record.name}',
				    map: map
				    });
				    markers.push(marker);
				  </g:each>
				
				  markers.each(function(marker) {
				    bounds.extend(marker.getPosition()); 
				
				    var li = new Element('li');
				    li.observe( 'click', function(event) { displayPoint(marker); } );	
			      li.update(marker.title);
				    $('list').insert(li);
				  });
				  }
		    </g:if>
		  </g:each>
			}
			
			function displayPoint(marker) {       
        map.panTo(marker.getPosition()); 
        infowindow.setContent(marker.title);
        infowindow.open(map, marker);
      }
      
			function handleChangeCenterOrZoom(event) {
			 var center = map.getCenter();
			 var zoom = map.getZoom();
			 var mapTypeId = map.getMapTypeId();
			 
			 new Ajax.Request('${createLink(controller: "geomap", action: "handleChangeCenterOrZoom")}', {
          method: 'post',
          parameters: { centerLat: center.lat(), centerLng: center.lng(), zoom: zoom, mapTypeId: mapTypeId }
         });
			}
			
      function savePoint(location) { 
       var name = $('name').value;
       var addressLine1 = $('addressLine1').value;
       var addressLine2 = $('addressLine2').value;
       var addressLine3 = $('addressLine3').value;
       var city = $('city').value;
       var state = $('state').value;
       var postalCode = $('postalCode').value;
      
       new Ajax.Request('${createLink(controller: "geomap", action: "addPoint")}', {
          method: 'post',
          parameters: { name: name, 
          addressLine1: addressLine1, addressLine2: addressLine2, addressLine3: addressLine3,
          city: city, state: state, postalCode: postalCode, 
          latitude: location.lat(), longitude: location.lng() },
          onSuccess: function(response) { 
           var json = response.responseText.evalJSON();
           addLocation(json.data); }
          });
      }
      
      // add a new location to the screen after it comes back from the server.
      function addLocation(location) {
        var marker = new google.maps.Marker({
				  position: new google.maps.LatLng(location.latitude, location.longitude),
				  title: location.name,
				  map: map
				  });
				  
				var li = new Element('li');
				li.observe( 'click', function(event) { displayPoint(marker); } );	
			  li.update(marker.title);
				$('list').insert(li);
				
				bounds.extend(marker.getPosition());
				zoomToBounds();
      }
      
      function geoEncode() { 
       var addressLine1 = $('addressLine1').value;
       var addressLine2 = $('addressLine2').value;
       var addressLine3 = $('addressLine3').value;
       var city = $('city').value;
       var state = $('state').value;
       var geoString = addressLine1;
       if (addressLine2) geoString += " " + addressLine2;
       if (addressLine3) geoString += " " + addressLine3;
       if (city) geoString += " " + city;
       if (state) geoString += " " + state;
       
       geocoder.geocode( { 'address': geoString }, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
          $('error').hide();
          var location = results[0].geometry.location;        
          savePoint(location);
        } else {        
          $('error').update(status);
          $('error').show();
        }
       });     
      }
      
      function zoomToBounds() { 
  		 map.fitBounds(bounds); 
	 		}
		</script>

		<div style="height:410px">
			<p>
				<b>Silicon Valley Recycled Water Systems</b>
				deliver over 10 million gallons of recycled water daily through over 100 miles of dedicated 
				pipeline. Use the map to learn more about these systems and their customers.  Find pipelines,
				customers, and your facilities by choosing overlays on the right.
			</p>
			<div>GeoScale: ${geoScale.name}</div>
			<div style="height:400px">
				<div id="map_canvas"></div>
				<g:form name="overlaySelectorForm" controller="geomap" action="changeOverlays">
				<ul id="list">
				<g:each in="${overlays}" var="overlay" >
				  <li style="background: skyblue">
				    <g:checkBox name="${overlay.label}" value="${overlay.shown}" 
								  onclick="overlaySelectorForm.submit();" />								
						${overlay.label}
				    <g:if test="${overlay.kmlFileUrl == null}">
				    (${overlay.records.size})
				    </g:if>
				  </li>
				</g:each>
				</ul>
				</g:form>
			</div>
		</div>
		<div style="clear:both"></div>		
		<script type="text/javascript">	
			mapInitialize();
	 </script>
	</body>
</html>
