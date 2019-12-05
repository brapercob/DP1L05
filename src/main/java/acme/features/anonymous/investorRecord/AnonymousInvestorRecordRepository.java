
package acme.features.anonymous.investorRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.investorRecords.InvestorRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousInvestorRecordRepository extends AbstractRepository {

	@Query("select i from InvestorRecord i")
	Collection<InvestorRecord> findManyInvestorRecords();

	@Query("select i from InvestorRecord i where i.id = ?1")
	InvestorRecord findOneInvestorRecordById(int id);

	//	List and show the top investor records, which are the ones with five stars.
	@Query("select i from InvestorRecord i where i.stars = 5")
	Collection<InvestorRecord> findInvestorRecordsWithFiveStars();

}
