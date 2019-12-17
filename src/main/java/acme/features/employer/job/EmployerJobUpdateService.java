
package acme.features.employer.job;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.administrator.customization.AdministratorCustomizationRepository;
import acme.features.employer.duty.EmployerDutyRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	// Internal state -------------------------

	@Autowired
	private EmployerJobRepository					repository;

	@Autowired
	private EmployerDutyRepository					dutyRepository;

	@Autowired
	private AdministratorCustomizationRepository	customizationRepository;


	// AbstractUpdateService<Employer,Job> interface --------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		Job j = this.repository.findOneJobById(request.getModel().getInteger("id"));
		Employer e = j.getEmployer();
		Principal p = request.getPrincipal();

		boolean result = p.getActiveRoleId() == e.getId();
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

		boolean finalMode;
		boolean hasDescriptor;
		boolean sumDuties = false;

		Job j = this.repository.findOneJobById(request.getModel().getInteger("id"));
		finalMode = j.getStatus().equals("published");
		hasDescriptor = j.getDescriptor() != null;

		if (hasDescriptor) {
			Collection<Duty> duties = this.dutyRepository.findManyByDescriptorId(j.getDescriptor().getId());
			Integer suma = 0;
			for (Duty d : duties) {
				suma += d.getAproxTime();
			}

			sumDuties = suma == 100;

		}

		if (finalMode) {
			errors.state(request, hasDescriptor, "status", "acme.validation.job.descriptor");
			errors.state(request, sumDuties, "status", "acme.validation.job.duties");
		}

		boolean isSpam;

		Collection<Customization> c = this.customizationRepository.findCustomization();
		String spamWords = "";
		Double threshold = 1.;
		Double spamCount = 0.;
		String sW = "";
		String body = request.getModel().getString("title").toUpperCase();

		for (Customization cus : c) {
			spamWords = cus.getSpamWords();
			threshold = cus.getThreshold();
		}
		Collection<String> spamList = Arrays.asList(spamWords.split(","));

		for (String s : spamList) {
			if (body.contains(s.toUpperCase())) {
				spamCount += 1.;
				sW = sW.concat(s + ",");
			}
		}

		isSpam = spamCount < threshold;
		if (finalMode) {
			errors.state(request, isSpam, "title", "acme.validation.job.spam", sW);
		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
