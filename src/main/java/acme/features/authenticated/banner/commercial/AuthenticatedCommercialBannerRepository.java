
package acme.features.authenticated.banner.commercial;

import acme.entities.banners.CommercialBanner;
import acme.framework.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AuthenticatedCommercialBannerRepository extends AbstractRepository {

	@Query("select cb from CommercialBanner cb")
	Collection<CommercialBanner> findMany();

	@Query("select cb from CommercialBanner cb where cb.id = ?1")
	CommercialBanner findOne(int id);

}
