
package acme.features.authenticated.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedDutyListService implements AbstractListService<Authenticated, Duty> {

	// Internal state ---------------------------------

	@Autowired
	private AuthenticatedDutyRepository repository;

	// AbstractListService<Employer,Duty> interface ----------


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");

	}

	@Override
	public Collection<Duty> findMany(final Request<Duty> request) {
		assert request != null;

		int descriptorId;
		Collection<Duty> result;

		descriptorId = request.getModel().getInteger("descriptorId");
		result = this.repository.findManyByDescriptorId(descriptorId);

		return result;
	}

}
