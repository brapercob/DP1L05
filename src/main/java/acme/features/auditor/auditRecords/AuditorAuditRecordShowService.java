
package acme.features.auditor.auditRecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.records.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditRecordShowService implements AbstractShowService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		//		boolean result;
		//		int auditRecodId;
		//		AuditRecord auditRecord;
		//		Auditor auditor;
		//		Principal principal;
		//
		//		auditRecodId = request.getModel().getInteger("id");
		//		auditRecord = this.repository.findOneAuditRecordById(auditRecodId);
		//		auditor = auditRecord.getAuditor();
		//		principal = request.getPrincipal();
		//		result = auditor.getUserAccount().getId() == principal.getAccountId();

		return true;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment", "body");

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.repository.findOneAuditRecordById(id);
		return res;
	}

}
