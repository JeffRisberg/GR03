package com.incra

import com.incra.controller.SecureController 

/**
 * The <i>PreferencesController</i> class implements the display and edit operations for preferences.
 *
 * @author Jeffrey Risberg
 * @since 10/14/10
 */
class PreferencesController extends SecureController {
  
  def index = {
    Preferences preferencesInstance = Preferences.get(1)
    
    [preferencesInstance : preferencesInstance]
  }
  
  def edit = {
    Preferences preferencesInstance = Preferences.get(1)
    def criteria
    def query
    
    criteria = GeoScale.createCriteria()
    query = {
      order("scale", "desc")
    }
    
    def geoScales = criteria.list(params, query)		
    
    criteria = TimeScale.createCriteria()
    query = {
      order("scale", "desc")
    }
    
    def timeScales = criteria.list(params, query)		
    
    [preferencesInstance : preferencesInstance, geoScales: geoScales, timeScales: timeScales]
  }
  
  def update = {
    def preferencesInstance = Preferences.get(params.id)
    if (preferencesInstance) {
      def geoScales = GeoScale.getAll()
      def timeScales = TimeScale.getAll()
      if (params.version) {
        def version = params.version.toLong()
        if (preferencesInstance.version > version) {
          
          preferencesInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'Preferences')] as Object[], "Another user has updated this User while you were editing")
          render(view: "edit", model: [preferencesInstance: preferencesInstance, geoScales : geoScales, timeScales : timeScales])
          return
        }
      }
      bindData(preferencesInstance, params, ['geoScale', 'timeScale'])
      preferencesInstance.geoScale = GeoScale.get(params.geoScale as Long)
      preferencesInstance.timeScale = TimeScale.get(params.timeScale as Long)
      preferencesInstance.validate()
      
      if (!preferencesInstance.hasErrors() && preferencesInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'Preferences'), preferencesInstance.id])}"
        redirect(action: "index", id: preferencesInstance.id)
      }
      else {
        render(view: "edit", model: [preferencesInstance: preferencesInstance, geoScales : geoScales, timeScales : timeScales])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
      redirect(action: "list")
    }
  }
  
  def cancel = { redirect(action: 'index')}
}
