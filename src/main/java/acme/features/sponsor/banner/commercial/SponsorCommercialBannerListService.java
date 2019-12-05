
package acme.features.sponsor.banner.commercial;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.features.authenticated.banner.commercial.AuthenticatedCommercialBannerRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SponsorCommercialBannerListService implements AbstractListService<Sponsor, CommercialBanner> {

	@Autowired
	private SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "targetURL");

	}

	@Override
	public Collection<CommercialBanner> findMany(final Request<CommercialBanner> request) {

		int id = request.getPrincipal().getAccountId()+1;
		List<CommercialBanner> result = (List<CommercialBanner>) this.repository.findMany(id);
		//return this.repository.findMany(id);
		return result;

	}

}
