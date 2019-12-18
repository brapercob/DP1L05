
package acme.features.employer.descriptor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.features.employer.duty.EmployerDutyRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerDescriptorShowService implements AbstractShowService<Employer, Descriptor> {

	// Internal state ----------------------------

	@Autowired
	private EmployerDescriptorRepository	repository;

	@Autowired
	private EmployerDutyRepository			dutyRepository;
	// AbstractShowService<Employer, Descriptor> interface -------------


	@Override
	public boolean authorise(final Request<Descriptor> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Descriptor> request, final Descriptor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "description");
		if (entity.getJob().getStatus().equals("published")) {
			model.setAttribute("isPublished", true);
		} else {
			model.setAttribute("isPublished", false);
		}

		Collection<Duty> duties = this.dutyRepository.findManyByDescriptorId(entity.getId());
		if (duties.isEmpty()) {
			model.setAttribute("hasDuties", false);
		} else {
			model.setAttribute("hasDuties", true);
		}

	}

	@Override
	public Descriptor findOne(final Request<Descriptor> request) {
		assert request != null;

		int jobId;
		Descriptor result;

		jobId = request.getModel().getInteger("jobId");
		result = this.repository.findOneDescriptorByJobId(jobId);

		return result;
	}

}
