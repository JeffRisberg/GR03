package com.incra.controller

import org.springframework.security.core.Authentication
import org.springframework.web.servlet.support.RequestContextUtils as RCU

import com.incra.User

/**
 * The <i>AbstractController</i> class is a superclass for controllers.
 * 
 * @author Jeff Risberg
 * @since 11/04/10
 */
class AbstractController {

  def grailsApplication

  /** Authenticate Service : IOC-Bean */
  def authenticateService;

  /** Authenticated user domain instance */
  public User loginUser

  /** is user logged on or not */
  boolean logon

  /** principal */
  def authPrincipal

  /** is Admin */
  boolean isAdmin

  /** is CompanyAdmin */
  boolean isCompanyAdmin

  /** Locale */
  Locale locale

  /** main request permission setting */
  def requestAllowed

  String header;
  String serverName

  def beforeInterceptor = { return beforeInterceptorExample(); }

  def beforeInterceptorExample() {
    header = request.getHeader("User-Agent") ?: "";
    header = header.toLowerCase();

    if (params['fromsite']) {
      session.fromsite = params['fromsite'];
    }

    serverName = request.serverName;
    isHttps = (request.scheme?.endsWith("s") || false);

    // TODO - Is this in use?
    if (isBB && isHttps) {
      String file = request.forwardURI;
      if (request.getQueryString() != null) {
        file += '?' + request.getQueryString();
      }
      URL reconstructedURL = new URL("http", //request.getScheme(),
          request.getServerName(),
          //                    request.getServerPort(),
          file);

      redirect(url: "${reconstructedURL.toString()}");
      return false;
    }

    if (requestAllowed != null && !authenticateService.ifAnyGranted(requestAllowed)) {
      redirect(uri: '/')
      return
    }

    Authentication authentication = SCH?.context?.authentication
    if (authentication) {
      authPrincipal = authentication?.principal
      if (authPrincipal != null && authPrincipal != 'anonymousUser') {
        loginUser = User.get(authPrincipal?.domainClass?.id);
      }
      if (loginUser) {
        session.loginUser = loginUser
        logon = true
        if (authenticateService) {
          isAdmin = authenticateService.ifAnyGranted('ROLE_ADMIN')
          isCompanyAdmin = authenticateService.ifAnyGranted('ROLE_COMPANY_ADMIN')
        }
      }
    }

    // i18n: if lang params
    if (params['lang']) {
      locale = new Locale(params['lang'])
      RCU.getLocaleResolver(request).setLocale(request, response, locale)
      session.lang = params['lang']
    }

    // need this for jetty
    if (session.lang != null) {
      locale = new Locale(session.lang)
      RCU.getLocaleResolver(request).setLocale(request, response, locale)
    }

    if (locale == null) {
      locale = RCU.getLocale(request)
    }

    response.setHeader('Cache-Control', 'no-cache') // HTTP 1.1
    response.setDateHeader('max-age', 0)
    response.setIntHeader('Expires', -1) //prevents caching at the proxy server
    response.addHeader('cache-Control', 'private') //IE5.x only
  }
}
