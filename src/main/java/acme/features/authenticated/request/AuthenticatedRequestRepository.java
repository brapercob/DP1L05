
package acme.features.authenticated.request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.request.Request_;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestRepository extends AbstractRepository {

	@Query("select r from Request_ r where r.id = ?1")
	Request_ findOneRequestById(int id);

	@Query("select r from Request_ r")
	Collection<Request_> findMany();

}
