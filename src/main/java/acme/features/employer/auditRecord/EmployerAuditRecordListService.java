
package acme.features.employer.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.records.AuditRecord;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class EmployerAuditRecordListService implements AbstractListService<Employer, AuditRecord> {

	// Internal state -----------------------------------------

	@Autowired
	private EmployerAuditRecordRepository repository;


	// AbstractListService<Authenticated,AuditRecord> interface --

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		boolean result = true;
		int jobId = request.getModel().getInteger("jobId");
		Collection<AuditRecord> records = this.repository.findManyByJobId(jobId);
		for (AuditRecord a : records) {
			if (a.getStatus().equals("draft")) {
				result = false;
				break;
			}
		}
		return result;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment");
	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> result;
		int jobId;

		jobId = request.getModel().getInteger("jobId");
		result = this.repository.findManyByJobId(jobId);

		return result;
	}

}
