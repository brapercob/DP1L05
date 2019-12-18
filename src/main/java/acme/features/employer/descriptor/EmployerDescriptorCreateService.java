
package acme.features.employer.descriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.employer.job.EmployerJobRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDescriptorCreateService implements AbstractCreateService<Employer, Descriptor> {

	// Internal state ----------------

	@Autowired
	private EmployerDescriptorRepository	repository;

	@Autowired
	private EmployerJobRepository			jobRepository;


	// AbstractCreateService<Employer,Descriptor> interface -------------

	@Override
	public boolean authorise(final Request<Descriptor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Descriptor> request, final Descriptor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description");
		model.setAttribute("jobId", entity.getJob().getId());

	}

	@Override
	public Descriptor instantiate(final Request<Descriptor> request) {
		assert request != null;

		Descriptor result;
		int jobId;
		Job job;

		result = new Descriptor();
		jobId = request.getModel().getInteger("jobId");
		job = this.jobRepository.findOneJobById(jobId);

		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<Descriptor> request, final Descriptor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Descriptor> request, final Descriptor entity) {
		assert request != null;
		assert entity != null;

		int jobId = request.getModel().getInteger("jobId");
		Job j = this.jobRepository.findOneJobById(jobId);

		entity.setJob(j);

		this.repository.save(entity);

	}

}
