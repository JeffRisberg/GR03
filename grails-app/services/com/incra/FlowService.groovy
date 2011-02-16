package com.incra

import java.util.List;

/**
 * The <i>FlowService</i> supports the Flowmodule.
 * 
 * @author Jeffrey Risberg
 * @since 11/20/10
 */
class FlowService {
	
	/**
	 * Return the valid Flows for a User
	 */
	List<Flow> getValidFlows(User currentUser) {
		List<Flow> allFlows = Flow.findAll()
		List<Flow> result = new ArrayList<Flow>();
		
		allFlows.each {
			if (it.user == null ||
			(currentUser != null && it.user?.id == currentUser.id)) {
				result.add(it)
			}
		}
		
		result
	}
	
	/**
	 * Save flow and amount of flow from an account to an account for a specific resource
	 */
	Flow saveFlow(Account fromAccount, Account toAccount,  UnitOfMeasure uom, Resource resource, double amount){
		Flow flow 
		List<Flow> flowlist = Flow.createCriteria().list{
			and {
				eq('fromAccount', fromAccount)
				eq('toAccount', toAccount)
				eq('resource', resource)
			} 
		 	
		}
		 if(flowlist.size()>0 && flowlist[0]!=null){
			throw new RuntimeException("Flow from ($fromAccount) to ($toAccount)already exists" );
		}else{
			  flow = new Flow(fromAccount: fromAccount, toAccount: toAccount, amount: amount, uom: uom, resource: resource)
			  flow.save()
		}
		
		flow
	}
}
