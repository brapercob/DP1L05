
package acme.features.employer.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.records.AuditRecord;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerAuditRecordShowService implements AbstractShowService<Employer, AuditRecord> {

	// Internal state -----------------------------------------

	@Autowired
	private EmployerAuditRecordRepository repository;


	// AbstractShowService<Authenticated,AuditRecord> interface -----

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		int recordId = request.getModel().getInteger("id");
		AuditRecord a = this.repository.findOneAuditRecordById(recordId);
		boolean result = a.getStatus().equals("published");
		return result;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "status", "body");
	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		int id = request.getModel().getInteger("id");
		AuditRecord result = this.repository.findOneAuditRecordById(id);

		return result;
	}

}
