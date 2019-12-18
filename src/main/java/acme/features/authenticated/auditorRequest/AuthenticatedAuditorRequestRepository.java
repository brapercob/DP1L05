
package acme.features.authenticated.auditorRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequests.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRequestRepository extends AbstractRepository {

	@Query("select ar from AuditorRequest ar")
	Collection<AuditorRequest> findMany();

	@Query("select a from Auditor a where a.userAccount.id = ?1")
	Auditor findAuditorByUserAccountId(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByUserAccountId(int id);

}
