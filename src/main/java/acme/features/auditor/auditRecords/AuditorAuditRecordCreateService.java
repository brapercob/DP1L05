
package acme.features.auditor.auditRecords;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.jobs.Job;
import acme.entities.records.AuditRecord;
import acme.entities.roles.Auditor;
import acme.features.administrator.customization.AdministratorCustomizationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository			repository;

	@Autowired
	AdministratorCustomizationRepository	customizationRepository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		boolean result;
		Principal principal;
		Auditor auditor;

		principal = request.getPrincipal();
		auditor = this.repository.findOneAuditorByUserAccountId(principal.getAccountId());

		result = principal.getActiveRoleId() == auditor.getId();

		return result;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment", "body");
		model.setAttribute("jobId", entity.getJob().getId());
	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord result;
		int jobId;
		Principal principal;
		int userAccountId;
		Auditor auditor;
		Job job;
		Date currentDate;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		auditor = this.repository.findOneAuditorByUserAccountId(userAccountId);
		currentDate = new Date(System.currentTimeMillis() - 1);

		result = new AuditRecord();

		result.setAuditor(auditor);
		result.setJob(job);
		result.setCreationMoment(currentDate);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean spamTitle, spamBody;

		Collection<Customization> c = this.customizationRepository.findCustomization();
		String spamWords = "";
		Double threshold = 1.;
		Double spamCountTitle = 0.;
		Double spamCountBody = 0.;

		String title = request.getModel().getString("title").toUpperCase();
		String body = request.getModel().getString("body").toUpperCase();

		for (Customization cus : c) {
			spamWords = cus.getSpamWords();
			threshold = cus.getThreshold();
		}
		List<String> spamList = Arrays.asList(spamWords.split(","));

		for (String s : spamList) {
			if (title.contains(s.toUpperCase())) {
				spamCountTitle += 1.;
			}
			if (body.contains(s.toUpperCase())) {
				spamCountBody += 1.;
			}
		}

		spamTitle = spamCountTitle < threshold;
		errors.state(request, spamTitle, "title", "acme.validation.spam");

		spamBody = spamCountBody < threshold;
		errors.state(request, spamBody, "body", "acme.validation.spam");

	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
