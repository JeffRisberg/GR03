package com.incra

import java.util.Date;
import java.util.List;

import com.incra.session.AccountSession;

/**
 * The <i>AccountService</i> supports the Account module.
 * 
 * @author Jeffrey Risberg
 * @since 11/20/10
 */
class AccountService {
	
	/**
	 * Get the AccountSession, creating it if needed.
	 */
	AccountSession getAccountSession(def session) {
		AccountSession aSession = (AccountSession) session.aSession;
		
		if (aSession == null) {
			aSession = new AccountSession();
			session.aSession = aSession;
		}
		return aSession
	}
	
	/**
	 * Return the valid Accounts for a User
	 */
	List<Account> getValidAccounts(User currentUser) {
		List<Account> allAcounts = Account.findAll()
		List<Account> result = new ArrayList<Account>();
		
		allAcounts.each {
			if (it.type?.name != "Customer") {
				if (it.user == null ||
				(currentUser != null && it.user?.id == currentUser.id)) {
					result.add(it)
				}
			}
		}
		
		result
	}
	
	/**
	 * Return the valid Accounts for a User at a specific GeoScale
	 */
	List<Account> getValidAccounts(User currentUser, GeoScale geoScale) {
		List<Account> allAcounts = Account.findAll()
		List<Account> result = new ArrayList<Account>();
		
		allAcounts.each {
			if (it.type?.name != "Customer") {
				if (it.geoScale.id == geoScale.id) {
					if (it.user == null ||
					(currentUser != null && it.user?.id == currentUser.id)) {
						result.add(it)
					}
				}
			}
		}
		
		result
	}
	
	/**
	 * Return the allowable create/edit types for an Account.
	 * @return
	 */ 
	List<AccountType> getValidAccountTypes() {
		List<AccountType> types = AccountType.findAll()
		List<AccountType> result = new ArrayList<AccountType>()
		
		types.each {
			if (it.name != "Customer")
				result.add(it)
		}
		result
	}
	
	/**
	 * Return the  parent and children accounts.
	 * @return
	 */
	List<Account> getAllParentAccounts() {
		List<AccountType> types = AccountType.findAll()
		List<AccountType> result = new ArrayList<AccountType>()
		
		types.each {
			if (it.name != "Customer")
				result.add(it)
		}
		result
	}
	
	/**
	 * Create an Account and if it has a parent an account link
	 * @param name
	 * @param address
	 * @param desc
	 * @param scale
	 * @param accountType
	 * @param parentAccount (optional)
	 * @param validFromDate
	 * @return
	 */
	Account saveAccountAndLink(String name, Address address, String desc, GeoScale scale,
	AccountType accountType, Account parentAccount, Date validFromDate) {
		
		Account account
		
		if (!name) {
			throw new RuntimeException("Account name must be specified" );
		}
		else {
			// check for existing Account
			account = Account.findByName(name)
			if(account){
				throw new RuntimeException("Account($name) already exists" );
			}else{
				account = new Account(type: accountType, name: name, geoScale: scale, address: address,
						description:desc)
				account.save()
			}
		}
		if (parentAccount){
			AccountLink	accountLink = new AccountLink(parent : parentAccount, child: account, validFromDate : validFromDate )
			accountLink.save()
		}
		
		account
	}
}
