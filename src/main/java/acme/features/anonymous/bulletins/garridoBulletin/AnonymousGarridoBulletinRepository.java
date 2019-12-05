
package acme.features.anonymous.bulletins.garridoBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.GarridoBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousGarridoBulletinRepository extends AbstractRepository {

	@Query("select g from GarridoBulletin g")
	Collection<GarridoBulletin> findMany();
}
