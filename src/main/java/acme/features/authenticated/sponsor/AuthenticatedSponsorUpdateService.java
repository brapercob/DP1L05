/*
 * AuthenticatedSponsorUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.sponsor;

import acme.entities.roles.Sponsor;
import acme.framework.components.*;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedSponsorUpdateService implements AbstractUpdateService<Authenticated, Sponsor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSponsorRepository repository;


	// AbstractUpdateService<Authenticated, Sponsor> interface -----------------

	@Override
	public boolean authorise(final Request<Sponsor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String pattern = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$";
		String creditCard = entity.getCreditCardNumber();
		if(creditCard.length() > 0) {
			boolean validCreditCard = creditCard.matches(pattern);
			errors.state(request, validCreditCard, "creditCardNumber", "error.creditcard.invalid");

		}

	}

	@Override
	public void bind(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Sponsor> request, final Sponsor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organizationName", "creditCardNumber");
	}

	@Override
	public Sponsor findOne(final Request<Sponsor> request) {
		assert request != null;

		Sponsor result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneSponsorByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Sponsor> request, final Sponsor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Sponsor> request, final Response<Sponsor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
