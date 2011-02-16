package com.incra

import com.incra.session.PageFrameworkSession;

/**
 * The <i>PageFrameworkService</i> maintains the page navigation options and history.
 * 
 * @author Jeffrey Risberg
 * @since 10/24/10
 */
class PageFrameworkService {
	
	String getSiteAddress() {
		return "http://ecocloud1.ning.com"
	}
	
	/**
	 * Access the session for this service, creating it if needed.
	 */
	PageFrameworkSession getPageFrameworkSession(def session) {
		PageFrameworkSession pfSession = (PageFrameworkSession) session.pfSession;
		
		if (pfSession == null) {
			pfSession = new PageFrameworkSession();
			session.pfSession = pfSession;
		}
		
		return pfSession
	}
	
	/** 
	 * Clear the show flags
	 */
	void reset(def session) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		
		pfSession.showScenarioSelector = false
		pfSession.showTimeSegmentSelector = false
		pfSession.showMetricsSelector = false;
	}
	
	void showScenarioSelector(def session) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		
		pfSession.showScenarioSelector = true
	}
	boolean isScenarioSelectorVisible(def session) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		
		pfSession.showScenarioSelector;
	}
	
	void showTimeSegmentSelector(def session) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		
		pfSession.showTimeSegmentSelector = true
	}
	boolean isTimeSegmentSelectorVisible(def session) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		
		pfSession.showTimeSegmentSelector;
	}
	
	void showMetricsSelector(def session) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		
		pfSession.showMetricsSelector = true
	}	
	boolean isMetricsSelectorVisible(def session) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		
		pfSession.showMetricsSelector;
	}
	
	void setControllerName(def session, String name) {
		session.controllerName = name;
	}	
	String getControllerName(def session) {
		session.controllerName;
	}
	
	void login(def session, User user, Profile profile) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		
		if (pfSession.user != null && pfSession.user.equals(user))
			return; // nothing to do
		
		pfSession.user = user;
		pfSession.profile = profile;
		
		user.lastLogin = new Date();
		user.loginCount++;
		
		user.save();
	}
	
	User getCurrentUser(def session) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		return pfSession.user;
	}
	
	/**
	 * Return a flag to indicate if the current user is an administrator.
	 * 
	 * @param session
	 * @return
	 */
	boolean userIsAdministrator(def session) {
		PageFrameworkSession pfSession = getPageFrameworkSession(session);
		User user = pfSession.user;
		
		if (user) {
			Role roleAdministrator = Role.get(1);
			Collection<UserRole> userRoles = UserRole.createCriteria().list() { eq('user', user) }
			
			for (UserRole userRole : userRoles) {
				if (userRole.role.equals(roleAdministrator)) {
					return true;
				}
			}
		}
		
		return false;
	}
}
