
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		Principal principal;
		Worker worker;

		principal = request.getPrincipal();
		worker = this.repository.findOneWorkerByUsserAccountId(principal.getAccountId());

		result = principal.getActiveRoleId() == worker.getId();

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "creationMoment", "status", "statement", "skills", "qualifications", "job.reference");
		model.setAttribute("jobId", entity.getJob().getId());
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;
		int jobId;
		Job job;
		Principal principal;
		Worker worker;
		Date currentDate;
		String status;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		principal = request.getPrincipal();
		worker = this.repository.findOneWorkerByUsserAccountId(principal.getAccountId());
		currentDate = new Date(System.currentTimeMillis() - 1);
		status = "pending";

		result = new Application();
		result.setCreationMoment(currentDate);
		result.setWorker(worker);
		result.setJob(job);
		result.setStatus(status);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Job job;
		int jobId;
		Date currentDate;
		Date deadline;
		boolean isPublished;
		boolean isOnDate;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		currentDate = new Date(System.currentTimeMillis());
		deadline = job.getDeadline();

		isPublished = job.getStatus().contains("published");
		isOnDate = deadline.after(currentDate);

		errors.state(request, isPublished, "Job.status", "acme.validation.application.is-published");
		errors.state(request, isOnDate, "Job.deadline", "acme.validation.application.is-on-date");

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
