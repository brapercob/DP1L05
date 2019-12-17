
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	// Internal state -----------------------

	@Autowired
	private EmployerJobRepository repository;


	// AbstractCreateService<Employer,Job> interface ----------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status");
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "link", "status");

	}

	@Override
	public Job instantiate(final Request<Job> request) {
		assert request != null;

		Job result = new Job();
		Principal p = request.getPrincipal();
		Employer e = this.repository.findOneEmployerById(p.getActiveRoleId());
		result.setStatus("draft");
		result.setEmployer(e);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		entity.setStatus("draft");
		this.repository.save(entity);
	}

}
