<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Set"%>
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jstl:set value="${companiesSector.text}" var="textCompany" />
<jstl:set value="${companiesSector.amount}" var="amountCompany" />
<jstl:set value="${investorGroupedBySector.text}" var="textIR" />
<jstl:set value="${investorGroupedBySector.amount}" var="amountIR" />

<jstl:set value="[${ratioOfPendingApplications}, ${ratioOfRejectedApplications}, ${ratioOfPublishedJobs}, ${ratioOfDraftJobs}]" var="list" />

<div>
	<canvas id="canvas"></canvas>
</div>
<div>
	<canvas id="canvas1"></canvas>
</div>

<fieldset>
	<spring:message code="numberOfAnnouncements" />
	: ${numberOfAnnouncements}
	<br>
	<spring:message code="numberOfCompanyRecords" />
	: ${numberOfCompanyRecords}
	<br>
	<spring:message code="numberOfInvestorRecords" />
	: ${numberOfInvestorRecords}
</fieldset>
<br>
<fieldset>
	<spring:message code="minimumRewardsRequest" />
	: ${minimumRewardsRequest}
	<br>
	<spring:message code="maximumRewardsRequest" />
	: ${maximumRewardsRequest}
	<br>
	<spring:message code="avgRewardsRequest" />
	: ${avgRewardsRequest}
	<br>
	<spring:message code="stddevRewardsRequest" />
	: ${stddevRewardsRequest}
</fieldset>
<br>
<fieldset>
	<spring:message code="minimumRewardsOffers" />
	: ${minimumRewardsOffers}
	<br>
	<spring:message code="maximumRewardsOffers" />
	: ${maximumRewardsOffers}
	<br>
	<spring:message code="avgRewardsOffers" />
	: ${avgRewardsOffers}
	<br>
	<spring:message code="stddevRewardsOffersFrom" />
	: ${stddevRewardsOffersFrom}
	<br>
	<spring:message code="stddevRewardsOffersTo" />
	: ${stddevRewardsOffersTo}
	<br>
</fieldset>
<br>
<fieldset>
	<spring:message code="averageNumberOfJobsPerEmployer"/>
	: ${averageNumberOfJobsPerEmployer}
	<br>
	<spring:message code="averageNumberOfApplicationsPerWorker"/>
	: ${averageNumberOfApplicationsPerWorker}
	<br>
	<spring:message code="averageNumberOfApplicationsPerEmployer"/>
	: ${averageNumberOfApplicationsPerEmployer}
	<br>
</fieldset>

<script type="text/javascript">

    <jstl:if test="${fn:length(amountCompany) != 0 || fn:length(amountIR) != 0}">
	var data = {
		labels: [],
		datasets:
				[
					<jstl:forEach var="i" begin="0" end="${fn:length(amountCompany) - 1}">
					{
						label: "\"${textCompany[i]}\"",
						data: [${amountCompany[i]}],
						backgroundColor: "#ff0000"
					},
					</jstl:forEach>
					<jstl:forEach var="i" begin="0" end="${fn:length(textIR) - 1}">
					<jstl:if test="${i != fn:length(textIR) - 1}">
					{
						label: "\"${textIR[i]}\"",
						data: [${amountIR[i]}],
						backgroundColor: "#0000ff"
					},
					</jstl:if>
					<jstl:if test="${i == fn:length(textIR) - 1}">
					{
						label: "\"${textIR[i]}\"",
						data: [${amountIR[i]}],
						backgroundColor: "#0000ff"
					}
					</jstl:if>
					</jstl:forEach>
				]
	};

	var data1 = {
		labels : ["Ratio of pending applications", "Ratio of rejected applications", "Ratio of published jobs", "Ratio of jobs in draft mode"],
		datasets : [{
			label: "",
			data: ${list},
			backgroundColor: ["#3cba9f","#e8c3b9", "#3e95cd", "#8e5ea2"]
		}]
	};

	var options =
			{
				legend: {display: false},
				scales:
						{
							yAxes: [{
								ticks: {
									suggestedMin: 0.0
								}
							}]
						}
			};
	var canvas = document.getElementById("canvas");
	var context = canvas.getContext("2d");
	new Chart
	(
			context,
			{
				type: "bar",
				data: data,
				options: options
			}
	);

	var options1 =
			{
				legend: {display: false},
				scales:
						{
							yAxes: [{
								ticks: {
									suggestedMin: 0.0
								}
							}]
						}
			};

	var canvas1 = document.getElementById("canvas1");
	var context1 = canvas1.getContext("2d");

	new Chart(
			context1,
			{
				type: 'bar',
				data: data1,
				options: options
			}
	);

	</jstl:if>
</script>