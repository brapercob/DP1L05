
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.worker.application.WorkerApplicationRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerJobShowService implements AbstractShowService<Employer, Job> {

	// Internal state ------------

	@Autowired
	EmployerJobRepository		repository;

	@Autowired
	WorkerApplicationRepository	applicationRepository;


	// AbstractListService<Employer, Job> interface -----------------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = job.getStatus().equals("published") || job.getStatus().equals("draft") && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "reference", "title", "deadline");
		request.unbind(entity, model, "salary", "link", "status");
		Job j = this.repository.findOneJobById(request.getModel().getInteger("id"));
		boolean hasDesc = false;
		boolean hasApps = false;
		if (j.getDescriptor() != null) {
			hasDesc = true;
		}
		Collection<Application> apps = this.applicationRepository.findApplicationsByJobId(entity.getId());
		if (!apps.isEmpty()) {
			hasApps = true;
		}
		model.setAttribute("hasDesc", hasDesc);
		model.setAttribute("hasApps", hasApps);
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

}
