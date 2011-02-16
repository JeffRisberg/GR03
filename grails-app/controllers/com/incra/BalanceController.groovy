package com.incra

import java.awt.BasicStroke 
import java.awt.FontMetrics;
import java.awt.Color 
import java.awt.Graphics2D;
import java.awt.Font 
import java.awt.RenderingHints 
import java.awt.image.BufferedImage 

/**
 * The <i>BalanceController</i> class implements operations to calculate a balance
 * and provide a display in the specified metrics.
 *
 * @author Jeffrey Risberg
 * @since 10/20/10
 */
class BalanceController {
	def pageFrameworkService
	def flowService
	def accountService
	
	static loggingInstructions = [
		index: [key: "Balance Display", severity: LogEntrySeverity.MEDIUM],
	]
	
	def index = {
		Preferences prefs = Preferences.get(1)
		pageFrameworkService.showScenarioSelector(session)
		pageFrameworkService.showTimeSegmentSelector(session)
		pageFrameworkService.showMetricsSelector(session)
		pageFrameworkService.setControllerName(session, "balance")
		
		[geoScale: prefs.geoScale]
	}
	
	def graph = {
		response.setContentType("image/png")
		response.setHeader("Cache-control", "no-cache")
		
		User currentUser = pageFrameworkService.getCurrentUser(session)
		Preferences preferences = Preferences.get(1)
		GeoScale geoScale = preferences.geoScale
		
		List<BalanceNode> nodes = new ArrayList<BalanceNode>()
		double maxAmount = 0.0
		int width = 700
		int height = 400
		
		// Find the set of visible accounts
		def accounts = accountService.getValidAccounts(currentUser, geoScale)
		
		// Find the set of visible flows
		def flows = flowService.getValidFlows(currentUser)
		
		// Populate the list of BalanceNodes
		accounts.each {			
			if (it.geoScale.id == geoScale.id) {
				if (true/*it.inBaseline || !isCurScenarioBaseline*/) {
					Account account = (Account) it
					BalanceNode node = new BalanceNode(account)
					
					node.height = 20
					node.width = 150
					
					flows.each {
						if (it.fromAccount.id.equals(account.id)) {
							// add to outbound
							node.addOutboundFlow((Flow) it)
							maxAmount = Math.max(maxAmount, it.amount)
						}
						
						if (it.toAccount.id.equals(account.id)) {
							// add to inbound
							node.addInboundFlow((Flow) it)
							maxAmount = Math.max(maxAmount, it.amount)
						}
					}
					nodes.add(node)
				}
			}
		}
		
		// Geographic placement
		int nextX = 10
		int nextY = 100
		int topX = 10
		int recoveryX = 300
		int bottomX = 10
		
		for (BalanceNode node : nodes) {
			if (node.inboundFlows.size() > 0 || node.outboundFlows.size() > 0) {
				// check if this goes on the top
				if (node.isTop()) {
					node.y = 10
					node.x = topX
					topX += 170
				}
				// check if this goes on the bottom
				else if (node.isBottom()) {
					node.y = 300
					node.x = bottomX
					bottomX += 170
				}
				// check if this goes in the recovery dev area
				else if (node.isRecoveryDevice()) {
					node.y = 265
					node.x = recoveryX
					recoveryX -= 170
				}
				else {
					node.x = nextX
					node.y = nextY
					
					nextX += 170
					if (nextX > 600) {
						nextX = 10
						nextY += 40
					}
				}
			}
		}
		
		// Prepare to draw
		System.setProperty("java.awt.headless", "true");
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		Font font = new Font("Sans-Serif", Font.BOLD, 14);
		g2d.setFont(font);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		FontMetrics fontMetrics = g2d.getFontMetrics();
		int fontHeight = fontMetrics.getHeight();
		int fontAscent = fontMetrics.getAscent();
		int fontDescent = fontMetrics.getDescent();
		
		BasicStroke[] strokes = new BasicStroke[6];
		for (int i = 0; i < 6; i++) {
			strokes[i] = new BasicStroke(i+1);
		}
		
		Color lightBlueColor = new Color(200, 200, 255);
		
		// Draw the background
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		
		// Draw the lines
		g2d.setColor(Color.BLACK);
		nodes.each {
			BalanceNode node = (BalanceNode) it
			
			node.outboundFlows.each {
				BalanceNode fromNode = getNode(nodes, it.fromAccount)
				BalanceNode toNode = getNode(nodes, it.toAccount)
				if (fromNode && toNode) {
					int midY = fromNode.centerY() + (toNode.centerY() - fromNode.centerY())/2
					double amount = it.amount;
					
					if (amount != 0.0) {
						int borderWidth = (int) (6.0*amount/maxAmount + 0.5)					
						
						g2d.setStroke(strokes[borderWidth-1]);
						
						g2d.drawLine(fromNode.centerX(), fromNode.centerY(), fromNode.centerX(), midY);					
						g2d.drawLine(fromNode.centerX(), midY, toNode.centerX(), midY);					
						g2d.drawLine(toNode.centerX(), midY, toNode.centerX(), toNode.centerY());
					}
				}
			}
		}
		g2d.setStroke(strokes[0]);
		
		// Draw the boxes
		nodes.each {
			BalanceNode node = (BalanceNode) it
			if (node.inboundFlows.size() > 0 || node.outboundFlows.size() > 0) {
				String name = node.account.name
				if (name.length() > 17) {
					name = name.substring(0, 17) + "..."
				}
				
				g2d.setColor(lightBlueColor);
				g2d.fillRect(node.x, node.y, node.width, node.height);
				
				g2d.setColor(Color.BLACK);
				int stringWidth = fontMetrics.getStringBounds(name, g2d).width;
				
				g2d.drawString(name, node.x + (node.width - stringWidth)/2, node.y + (node.height - fontHeight)/2 + fontAscent - 1);
				
				if (node.netInflow != 0) {
					String str = "" + node.netInflow;
					
					g2d.drawString(str, node.x + node.width/2 + 5, node.y - 2);
				}
				if (node.netOutflow != 0) {
					String str = "" + node.netOutflow;
					
					g2d.drawString(str, node.x + node.width/2 + 5, node.y + node.height + 13);
				}
			}
		}
		
		if (nodes.size() == 0) {
			g2d.drawString("Please create Facilities and their Flows to allow Balance calculation.", 20, 20)
		}
		
		// Complete the image generation and sending
		OutputStream out = response.getOutputStream();
		javax.imageio.ImageIO.write(bufferedImage, "PNG", out);
		out.close();
	}
	
	def changeScenario = {
		Scenario scenario = Scenario.get(params.scenarioId)
		
		session.scenario = scenario
		redirect(action: "index")
	}
	
	def changeTimeSegment = {
		TimeSegment timeSegment = TimeSegment.get(params.timeSegmentId)
		
		session.timeSegment = timeSegment
		redirect(action: "index")
	}
	
	def changeMetric = {
		int metric = params.metric as int
		
		session.metric = metric
		redirect(action: "index")
	}
	
	protected BalanceNode getNode(List<BalanceNode> nodes, Account account) {
		for (BalanceNode node : nodes) {
			if (node.account == account)
				return node;
		}
		return null;
	}
}
