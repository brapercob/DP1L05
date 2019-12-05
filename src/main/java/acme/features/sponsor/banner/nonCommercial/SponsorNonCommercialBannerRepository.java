
package acme.features.sponsor.banner.nonCommercial;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select ncb from NonCommercialBanner ncb where ncb.sponsor.id = ?1")
	Collection<NonCommercialBanner> findMany(int id);

	@Query("select ncb from NonCommercialBanner ncb where ncb.id = ?1")
	NonCommercialBanner findOne(int id);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findSponsor(int id);

}
