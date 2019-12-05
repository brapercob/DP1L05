
package acme.features.auditor.auditRecords;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.records.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditRecordListService implements AbstractListService<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int jobId = request.getModel().getInteger("jobId");
		Collection<AuditRecord> records;
		boolean result = true;

		records = this.repository.findManyByJobId(jobId);
		for (AuditRecord a : records) {
			if (a.getAuditor().getId() != principal.getActiveRoleId() && a.getStatus().equals("draft")) {
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

		request.unbind(entity, model, "title", "status", "creationMoment");
	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> res;
		Principal principal;

		principal = request.getPrincipal();
		int jobId = request.getModel().getInteger("jobId");
		res = this.repository.findManyByJobId(jobId);
		for (AuditRecord a : res) {
			if (a.getAuditor().getId() != principal.getActiveRoleId() && a.getStatus().equals("draft")) {
				res.remove(a);
			}
		}

		return res;
	}

}
