
package acme.features.worker.application;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.customization.Customization;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.features.administrator.customization.AdministratorCustomizationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository				repository;

	@Autowired
	AdministratorCustomizationRepository	customizationRepository;


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

		//Checking the Job

		Job job;
		int jobId;
		Date currentDate;
		Date deadline;
		boolean isPublished, isOnDate;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		currentDate = new Date(System.currentTimeMillis());
		deadline = job.getDeadline();

		isPublished = job.getStatus().contains("published");
		isOnDate = deadline.after(currentDate);

		errors.state(request, isPublished, "Job.status", "acme.validation.application.is-published");
		errors.state(request, isOnDate, "Job.deadline", "acme.validation.application.is-on-date");

		//Cheking the spamWords

		Collection<Customization> c = this.customizationRepository.findCustomization();
		String spamWords = "";
		Double threshold = 1.;

		for (Customization cus : c) {
			spamWords = cus.getSpamWords();
			threshold = cus.getThreshold();
		}
		List<String> spamList = Arrays.asList(spamWords.split(","));

		String statement = request.getModel().getString("statement").toUpperCase();
		String skills = request.getModel().getString("skills").toUpperCase();
		String qualifications = request.getModel().getString("qualifications").toUpperCase();
		boolean spamStatement, spamSkills, spamQualifications;
		double sCounterStat = 0.;
		double sCounterSkills = 0.;
		double sCounterQual = 0.;

		for (String s : spamList) {
			if (statement.contains(s.toUpperCase())) {
				sCounterStat += 1.;
			}
			if (skills.contains(s.toUpperCase())) {
				sCounterSkills += 1.;
			}
			if (qualifications.contains(s.toUpperCase())) {
				sCounterQual += 1.;
			}
		}

		spamStatement = sCounterStat < threshold;
		spamSkills = sCounterSkills < threshold;
		spamQualifications = sCounterQual < threshold;

		errors.state(request, spamStatement, "statement", "acme.validation.spam");
		errors.state(request, spamSkills, "skills", "acme.validation.spam");
		errors.state(request, spamQualifications, "qualifications", "acme.validation.spam");

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
