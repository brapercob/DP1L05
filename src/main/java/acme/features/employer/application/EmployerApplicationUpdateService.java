
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	private EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		Employer logged = this.repository.findEmployer(principal.getAccountId());
		int id = request.getModel().getInteger("id");
		Application ap = this.repository.findApplicationById(id);
		Employer employer = ap.getJob().getEmployer();

		return employer == logged;
	}

	// De la vista a la base de datos
	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	// De la base de datos a la vista
	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "creationMoment", "status", "statement", "skills", "qualifications", "justification");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;
		Application res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findApplicationById(id);
		return res;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (entity.getStatus().equals("rejected")) {
			boolean statusEmpty = !entity.getJustification().equals("");
			errors.state(request, statusEmpty, "justification", "error.justification.null");
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Application ap = this.repository.findApplicationById(entity.getId());

		ap.setStatus(entity.getStatus());
		ap.setJustification(entity.getJustification());

		this.repository.save(ap);
	}
}
