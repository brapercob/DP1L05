
package acme.features.sponsor.banner.commercial;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	@Autowired
	private SponsorCommercialBannerRepository repository;



	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Sponsor sponsor = this.repository.findSponsor(request.getPrincipal().getAccountId() + 1);
		boolean invalidCreditCard = sponsor.getCreditCardNumber() != null && !sponsor.getCreditCardNumber().equals("");

		request.unbind(entity, model, "picture", "targetURL", "slogan");
		request.transfer(model, "invalidCreditCard");

	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		CommercialBanner cb = new CommercialBanner();
		Date date = new Date();
		cb.setPicture("");
		cb.setSlogan("");
		cb.setTargetURL("");
		Sponsor sponsor = this.repository.findSponsor(request.getPrincipal().getAccountId() + 1);
		cb.setSponsor(sponsor);

		return cb;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Sponsor sponsor = this.repository.findSponsor(request.getPrincipal().getAccountId() + 1);
		boolean invalidCreditCard = sponsor.getCreditCardNumber() != null && !sponsor.getCreditCardNumber().equals("");
		errors.state(request, invalidCreditCard, "creditCardNumber", "error.principal.creditCardNumber");

	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
