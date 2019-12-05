
package acme.features.sponsor.banner.commercial;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select cb from CommercialBanner cb where cb.sponsor.id = ?1")
	Collection<CommercialBanner> findMany(int id);

	@Query("select cb from CommercialBanner cb where cb.id = ?1")
	CommercialBanner findOne(int id);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findSponsor(int id);

}
