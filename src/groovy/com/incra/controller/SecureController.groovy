package com.incra.controller

import org.codehaus.groovy.grails.plugins.springsecurity.Secured

/**
 * The <i>SecureController</i> is an abstract controller class for operations
 * that require a logged-in user.
 * 
 * @author Jeff Risberg
 * @since 11/12/10
 */
@Secured(['ROLE_USER'])
class SecureController extends AbstractController {
}
