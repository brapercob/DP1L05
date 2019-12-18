
package acme.features.administrator.auditorRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.userAccount.id = ?1")
	Auditor findOneAuditorByUserAccountId(int id);

}
