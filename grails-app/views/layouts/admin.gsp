<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <p:css name="bundle" />   
        <p:css name="start/jquery-ui-1.8.6.custom" />
        <p:javascript src="jquery-and-ui" />
        <g:layoutHead />      
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div style="background: blue; width:600px">
            GR03 admin baseline
        </div>
        <g:layoutBody />
    </body>
</html>