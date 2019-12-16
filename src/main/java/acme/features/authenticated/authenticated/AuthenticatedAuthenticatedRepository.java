
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuthenticatedRepository extends AbstractRepository {

	@Query("select au from Authenticated au where au.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select au from Authenticated au")
	Collection<Authenticated> findManyAuthenticated();

	@Query("select t.participants from Thread t where t.id = ?1")
	Collection<Authenticated> findAuthenticatedByThreadId(int id);

}
