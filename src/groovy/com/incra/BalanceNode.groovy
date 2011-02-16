package com.incra

/**
 * The <i>BalanceNode</i> is a DTO that holds information collected for the balance display.
 * 
 * @author Jeff Risberg
 * @since 10/22/10
 */
class BalanceNode {
	Account account
	int x
	int y
	int width
	int height
	double value;
	int netInflow
	int netOutflow
	List<Flow> inboundFlows
	List<Flow> outboundFlows
	
	BalanceNode(Account account) {
		this.account = account;
		this.value = 0;
		
		inboundFlows = new ArrayList<Flow>()
		outboundFlows = new ArrayList<Flow>()
	}
	
	void addInboundFlow(Flow flow) {
		inboundFlows.add(flow);
		netInflow += flow.amount
		value = value + flow.amount;
	}
	
	void addOutboundFlow(Flow flow) {
		outboundFlows.add(flow);
		netOutflow += flow.amount
		value = value - flow.amount;
	}
	
	int centerX() {
		x + width/2
	}
	
	int centerY() {
		y + height/2
	}
	
	boolean isTop() {
		outboundFlows.size() > 0 && inboundFlows.size() == 0
	}
	
	boolean isRecoveryDevice() {
		account.type.name == "Recovery Device"
	}
	
	boolean isBottom() {
		inboundFlows.size() > 0 && outboundFlows.size() == 0
	}
}
