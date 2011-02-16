package com.incra

import com.incra.controller.SecureController 
import com.incra.pojo.DisplayFilterPojo;

/**
 * The <i>AccountController</i> is based on generated code.
 *
 * @author Jeffrey Risberg
 * @since 10/12/10
 */
class AccountController extends SecureController {
  
  def pageFrameworkService
  
  static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
  
  def index = {
    redirect(action: "list", params: params)
  }
  
  def list = {
    params.max = Math.min(params.max ? params.int('max') : 10, 100)
    
    User currentUser = pageFrameworkService.getCurrentUser(session);
    
    Preferences prefs = Preferences.get(1)
    List<DisplayFilterPojo> filters = new ArrayList<DisplayFilterPojo>()
    DisplayFilterPojo dfp;
    
    dfp = new DisplayFilterPojo(name: 'name', label: 'Name:', type: 'String')
    filters.add(dfp)
    
    dfp = new DisplayFilterPojo(name: 'accountType', label: 'Account Type:', type: 'Select',
    values: AccountType.list())
    filters.add(dfp)
    
    // Keep these values so we can rerender while maintaining filter value settings
    flash.name = params.name
    flash.accountType = params.accountType
    
    def criteria = Account.createCriteria()
    def query = {
      and {
        if (params.name && params.name.trim()) {
          like("name", params.name + '%')
        }
        if (params.accountType) {
          def selectedAccountType = TransactionType.get(Integer.parseInt(params.accountType))
          if (selectedAccountType) {
            eq('type', selectedAccountType)
          }
        }
        eq('geoScale', prefs.geoScale)
        if (currentUser != null) {
          or {
            isNull('user')						
            eq('user', currentUser)
          }
        }
        if (currentUser == null) {
          isNull('user')
        }
      }
    }
    
    def results = criteria.list(params, query)
    
    [filters: filters, accountInstanceList: results, accountInstanceTotal: results.totalCount]
  }
  
  def create = {
    def accountInstance = new Account()
    accountInstance.properties = params
    return [accountInstance: accountInstance]
  }
  
  def save = {
    def accountInstance = new Account(params)
    if (accountInstance.save(flush: true)) {
      flash.message = "${message(code: 'default.created.message', args: [message(code: 'account.label', default: 'Account'), accountInstance.id])}"
      redirect(action: "show", id: accountInstance.id)
    }
    else {
      render(view: "create", model: [accountInstance: accountInstance])
    }
  }
  
  def show = {
    def accountInstance = Account.get(params.id)
    if (!accountInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
      redirect(action: "list")
    }
    else {
      [accountInstance: accountInstance]
    }
  }
  
  def edit = {
    def accountInstance = Account.get(params.id)
    if (!accountInstance) {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
      redirect(action: "list")
    }
    else {
      return [accountInstance: accountInstance]
    }
  }
  
  def update = {
    def accountInstance = Account.get(params.id)
    if (accountInstance) {
      if (params.version) {
        def version = params.version.toLong()
        if (accountInstance.version > version) {
          
          accountInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'account.label', default: 'Account')] as Object[], "Another user has updated this Account while you were editing")
          render(view: "edit", model: [accountInstance: accountInstance])
          return
        }
      }
      accountInstance.properties = params
      if (!accountInstance.hasErrors() && accountInstance.save(flush: true)) {
        flash.message = "${message(code: 'default.updated.message', args: [message(code: 'account.label', default: 'Account'), accountInstance.id])}"
        redirect(action: "show", id: accountInstance.id)
      }
      else {
        render(view: "edit", model: [accountInstance: accountInstance])
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
      redirect(action: "list")
    }
  }
  
  def delete = {
    def accountInstance = Account.get(params.id)
    if (accountInstance) {
      try {
        accountInstance.delete(flush: true)
        flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
        redirect(action: "list")
      }
      catch (org.springframework.dao.DataIntegrityViolationException e) {
        flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
        redirect(action: "show", id: params.id)
      }
    }
    else {
      flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])}"
      redirect(action: "list")
    }
  }
}
