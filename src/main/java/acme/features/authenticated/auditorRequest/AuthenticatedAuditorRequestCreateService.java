
package acme.features.authenticated.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorRequestCreateService implements AbstractCreateService<Authenticated, AuditorRequest> {

	// Internal state ---------------------------

	@Autowired
	private AuthenticatedAuditorRequestRepository repository;


	// AbstractCreateService<Authenticated, AuditorRequest> interface -------------

	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;
		boolean result = true;
		Principal principal = request.getPrincipal();
		Auditor a = this.repository.findAuditorByUserAccountId(principal.getAccountId());
		if (a != null) {
			result = false;
		}
		return result;
	}

	@Override
	public void bind(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert model != null;
		assert entity != null;

		request.unbind(entity, model, "firm", "statement");
	}

	@Override
	public AuditorRequest instantiate(final Request<AuditorRequest> request) {
		assert request != null;

		AuditorRequest result;
		Authenticated authenticated;

		authenticated = this.repository.findAuthenticatedByUserAccountId(request.getPrincipal().getAccountId());
		result = new AuditorRequest();
		result.setAuthenticated(authenticated);

		return result;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
