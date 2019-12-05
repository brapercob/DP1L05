
package acme.features.anonymous.bulletins.brionesBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.BrionesBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousBrionesBulletinRepository extends AbstractRepository {

	@Query("select b from BrionesBulletin b")
	Collection<BrionesBulletin> findMany();

}
