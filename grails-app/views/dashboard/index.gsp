<html>
	<head>
		<title>Dashboard Main</title>
		<meta name="layout" content="main" />
		<p:javascript src='highcharts/highcharts' />
		<p:css name="dashboard" />
	</head>
	<body>
    <div class="panel" style="width: 900px">
    <div class="pageLeftTitle">
    <g:link action="index" id="1">
      <g:if test="${chartView}">
        <span class="selected">Chart View</span>
      </g:if>
      <g:if test="${!chartView}">
        Chart View
      </g:if>
    </g:link> 
    | 
    <g:link action="index id="0">
      <g:if test="${!chartView}">
        <span class="selected">Table View</span>
      </g:if>
      <g:if test="${chartView}">
        Table View
      </g:if>
    </g:link>
    </div>
    <div style="clear:both"></div>
    
    <g:each in="${panels}" var="panel" status="panelIndex">
    <div class="dbPanel">
      <div class="dbTitle">
        ${panel.title}
      </div>
      <div>
	      <table>
	        <g:each in="${panel.leftRows}" var="row">
	          <tr>
	            <td style="padding: 3px 2px !important">${row.key}</td>
	            <td style="padding: 3px 2px !important; text-align: right">${row.value}</td>
	          </tr>
	        </g:each>
	      </table>
      </div>
      <g:if test="${panel.rightRows && !panel.chart}">
      <div>
        <table>
        <g:each in="${panel.rightRows}" var="row">
          <tr>
             <td style="padding: 3px 2px !important">
               <g:if test="${panel.controller != null && row.id}">
                 <g:link controller="${panel.controller}" action="show" id="${row.id}">
                   ${row.key}
                 </g:link>
               </g:if>
               <g:else>
                 ${row.key}
               </g:else>
             </td>
	           <td style="padding: 3px 2px !important; text-align: right">${row.value}</td>
          </tr>
        </g:each>
        </table>
      </div>
      </g:if>    
      <g:if test="${panel.rightRows && panel.chart}">
      <div id="chart-container-${panelIndex}" style="width: 100%; height: 195px"></div>
      <script type="text/javascript">      
      $(document).ready(function() {       
      chart1 = new Highcharts.Chart({
         chart: {
            renderTo: 'chart-container-${panelIndex}',
            defaultSeriesType: 'column'
         },
         credits: {
			      enabled: false
			   },
         title: {
            text: '${panel.title}'
         },
         legend: {
			      enabled: false
			   },
			   plotOptions: {
			      column: {
			        color: '${panel.color}',
			      },
			   }, 
         xAxis: {
            categories: [ <g:each in="${panel.rightRows}" var="x">'${x.key}',</g:each> ]
         },
         yAxis: {
            title: {
               text: null
            }
         },
         series: [{  
            name: "Counts in last quarter",          
            data: [ <g:each in="${panel.rightRows}" var="x">${x.value},</g:each> ]
         }]
      });      
      });
      </script>
      </g:if> 
    </div>
    </g:each>
    <div style="clear:both"></div>		
		</div>
	</body>
</html>
