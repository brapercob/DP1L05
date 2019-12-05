
package acme.features.sponsor.banner.nonCommercial;

import acme.entities.banners.CommercialBanner;
import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.features.authenticated.banner.noncommercial.AuthenticatedNonCommercialBannerRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorNonCommercialBannerShowService implements AbstractShowService<Sponsor, NonCommercialBanner> {

	@Autowired
	private SponsorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int id = request.getModel().getInteger("id");
		NonCommercialBanner ncm = this.repository.findOne(id);
		Sponsor actual = this.repository.findSponsor(principal.getAccountId());
		boolean res = actual != null && actual == ncm.getSponsor();

		return res;
	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetURL", "jingle");
	}

	@Override
	public NonCommercialBanner findOne(final Request<NonCommercialBanner> request) {

		return this.repository.findOne(request.getModel().getInteger("id"));
	}

}
