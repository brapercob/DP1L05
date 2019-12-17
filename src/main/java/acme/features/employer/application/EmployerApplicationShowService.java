
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationShowService implements AbstractShowService<Employer, Application> {

	// Internal state ------------

	@Autowired
	EmployerApplicationRepository repository;

	// AbstractListService<Employer, Application> interface -----------------


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Employer employer;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findApplicationById(applicationId);
		principal = request.getPrincipal();
		employer = this.repository.findEmployer(principal.getAccountId());

		result = employer != null && application.getJob().getEmployer() == employer;

		return result;
	}

	// De la base de datos a la vista
	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "creationMoment", "status", "statement", "skills", "qualifications", "job.reference", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findApplicationById(id);

		return result;
	}

}
