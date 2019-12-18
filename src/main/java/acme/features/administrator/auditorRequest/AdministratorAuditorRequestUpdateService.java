
package acme.features.administrator.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequests.AuditorRequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorRequestUpdateService implements AbstractUpdateService<Administrator, AuditorRequest> {

	// Internal state ----------------------------

	@Autowired
	private AdministratorAuditorRequestRepository	repository;

	@Autowired
	private AdministratorAuditorRepository			auditorRepository;


	// AbstractUpdateService<Administrator, AuditorRequest> interface -------------

	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;
		return true;
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
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "statement");
	}

	@Override
	public AuditorRequest findOne(final Request<AuditorRequest> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		AuditorRequest result = this.repository.findOneAuditorRequestById(id);

		return result;
	}

	@Override
	public void validate(final Request<AuditorRequest> request, final AuditorRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isauditor;
		Authenticated a = entity.getAuthenticated();
		Auditor au = this.auditorRepository.findOneAuditorByUserAccountId(a.getUserAccount().getId());
		isauditor = au == null;
		errors.state(request, isauditor, "firm", "acme.validation.auditorRequest");

	}

	@Override
	public void update(final Request<AuditorRequest> request, final AuditorRequest entity) {
		assert request != null;

		Authenticated a = entity.getAuthenticated();
		UserAccount ua = a.getUserAccount();
		Auditor au = new Auditor();

		au.setUserAccount(ua);
		au.setFirm(entity.getFirm());
		au.setStatement(entity.getStatement());

		this.repository.delete(entity);
		this.auditorRepository.save(au);

	}

}
