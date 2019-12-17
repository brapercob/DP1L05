
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.worker.application.WorkerApplicationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	// Internal state ------------------------------

	@Autowired
	private EmployerJobRepository		repository;

	@Autowired
	private WorkerApplicationRepository	applicationRepository;


	// AbstractDeleteService<Employer,Job> interface -----------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		Job j = this.repository.findOneJobById(request.getModel().getInteger("id"));
		Principal p = request.getPrincipal();
		Employer e = j.getEmployer();

		boolean result = e.getId() == p.getActiveRoleId();
		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "link", "status");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result = this.repository.findOneJobById(request.getModel().getInteger("id"));

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int jobId = request.getModel().getInteger("id");
		Collection<Application> applications = this.applicationRepository.findApplicationsByJobId(jobId);

		boolean noApplications = applications.isEmpty();

		errors.state(request, noApplications, "status", "acme.validation.job.applications");
	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;

		this.repository.delete(entity);
	}

}
