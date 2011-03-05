<html>
    <head>
    <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
    <g:javascript library="jquery" plugin="jquery" />
    <p:css name="bundle" />       
    <p:javascript src="jquery-and-ui" />
    <g:layoutHead />
    </head>
    <body style="margin: 0px auto; width: 955px">
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="header">
           <p>GR03 Baseline</p>
           <i:renderMenu />
        </div>
        <g:layoutBody />
        <div style="clear:both"></div>
        <div id="footer">
        <g:render template="/footer"/>
        </div>
    </body>
</html>