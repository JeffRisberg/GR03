package com.incra

/**
 * The <i>TransactionService</i> supports the Transaction module.
 * 
 * @author Jeffrey Risberg
 * @since 11/20/10
 */
class TransactionService {
	/**
	* Update transaction with Start Date
	* @param transaction
	* @param endDate
	*/
	void updateStartDate(Transaction transaction, Date startDate){
		if(transaction){
			transaction.startDate = startDate
			transaction.save()
		}else{
			throw new RuntimeException("Start date cannot be updated")
		}
	}
	
	/**
	 * Update transaction with its End Date
	 * @param transaction
	 * @param endDate
	 */
	void updateEndDate(Transaction transaction, Date endDate){
		if(transaction){
			transaction.endDate = endDate
			transaction.save()
		}else{
			throw new RuntimeException("End date cannot be updated")
		}
	}
	
	/**
	 * Create a Transaction 
	 */
	Transaction saveTransaction(Account account,TransactionType type, Resource resource, UnitOfMeasure uom, double amount) {
		
		Transaction transaction
		if(!account){
			throw new RuntimeException("Transaction cannot be created as the Account($account)does not exist" );
		}else{
			transaction = new Transaction(account: account, type: type, resource: resource,
					amount: amount, uom: uom)
			transaction.save()
		}
		transaction
	}
}
	