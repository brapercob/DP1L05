
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						numberOfAnnouncements;
	Integer						numberOfCompanyRecords;
	Integer						numberOfInvestorRecords;

	// Request queries

	Double						minimumRewardsRequest;
	Double						maximumRewardsRequest;
	Double						avgRewardsRequest;
	Double						stddevRewardsRequest;

	// Offers queries
	Double						minimumRewardsOffers;
	Double						maximumRewardsOffers;
	Double						avgRewardsOffers;
	Double						stddevRewardsOffersFrom;
	Double						stddevRewardsOffersTo;

	//Chartjs
	GroupedBy					companiesSector;
	GroupedBy					investorGroupedBySector;
	String[]					labels;


	Double averageNumberOfJobsPerEmployer;
	Double averageNumberOfApplicationsPerWorker;
	Double averageNumberOfApplicationsPerEmployer;
	Double ratioOfPendingApplications;
	Double ratioOfRejectedApplications;
	Double ratioOfPublishedJobs;
	Double ratioOfDraftJobs;
}
