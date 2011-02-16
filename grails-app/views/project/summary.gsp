<style type="text/css">
    .blueFull { background: #C0D9AF; color: black; }
    .blueGap  { background: #C0D9AF; color: black; border-right: 5px solid white; }
</style>

<p>Name: ${projectInstance.name}</p>
<p>Type: ${projectInstance.type.name}, Priority: <i:priorityLabel value="${projectInstance.priority}" /></p>

<p>Current Usage (gals): <g:formatNumber format="##,###,##0" number="${roiProjectInstance?.currentMonthlyWaterUse}" /></p>

<div style="border: 1px solid black">
<table style="padding: 2px; border: 1px solid white !important">
	<tr>
		<td class="blueGap">Current</td>
		<td class="blueFull">Proposed</td>
	</tr>
	<tr>
		<td>Cycles: ${roiProjectInstance?.currentCycles}</td>
		<td>Cycles: ${roiProjectInstance?.proposedCycles}</td>
	</tr>
	<tr>
		<td>
		  <g:if test="${roiProjectInstance?.currentProvider}">
		    ${roiProjectInstance?.currentProvider?.name} (freshwater)
		  </g:if>
		  <g:else>
		    <i>No current provider specified</i>
		  </g:else>
		</td>
		<td>
		  <g:if test="${roiProjectInstance?.proposedProvider}">
		    ${roiProjectInstance?.proposedProvider?.name} (recycled)
		  </g:if>
		  <g:else>
		    <i>No proposed provider specified</i>
		  </g:else>
		</td>
	</tr>
	<tr>
		<td>Rate ($/HCF): ${pcd.currentRate}</td>
		<td>Rate ($/HCF): ${pcd.proposedRate}</td>
	</tr>
	<tr>
		<td>Usage (HCF): 
		<g:formatNumber format="##,###,##0" number="${pcd.currentUsageHCF}" />
		</td>
		<td>Usage (HCF): 
		<g:formatNumber format="##,###,##0" number="${pcd.proposedUsageHCF}"/>
		</td>
	</tr>
	<tr>
		<td>Chemical Costs ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.currentChemicalCosts}"/></td>
		<td>Chemical Costs ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.proposedChemicalCosts}"/></td>
	</tr>
	<tr>
		<td>Admin,Mon,Test Costs ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.currentAMTCosts}"/></td>
		<td>Admin,Mon,Test Costs ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.proposedAMTCosts}"/></td>
	</tr>
	<tr>
		<td>Total Cost ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.totalCurrentCosts}"/></td>
		<td>Total Cost ($/month): <g:formatNumber format="##,###,##0.00" number="${pcd.totalProposedCosts}"/></td>
	</tr>
</table>
</div>

<p>Cost Savings ($/month): <g:formatNumber format="##,###,##0.00" number="${projectInstance.monthlySavings}" /></p>
<p>Total Pipeline Feet of Construction: ${pcd.totalPipelineFeet}</p>
<p>Infrastructure Improvements Selected:<br/>
<span style="font-size: 12px">${pcd.infrastructureOptions}</span></p>

<table style="background: skyblue;">
	<tr>
		<td style="padding: 1px">
			Budget ($):
			<g:formatNumber number="${projectInstance.budget}"
				format="##,###,###" />
		</td>
		<td style="padding: 1px">
			Investment ($):
			<g:formatNumber number="${projectInstance.capitalInvest}"
				format="##,###,###" />
		</td>
		<td style="padding: 1px">
			Payback (months):
			<g:formatNumber number="${projectInstance.paybackMonths}"
				format="###,##0.0" />&nbsp;
		</td>
		<td style="padding: 1px">
			ROI:
			<g:formatNumber number="${projectInstance.roi}"
				format="#,##0.00" />
			%
		</td>
	</tr>
</table>
