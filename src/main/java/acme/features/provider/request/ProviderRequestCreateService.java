
package acme.features.provider.request;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.request.Request_;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestCreateService implements AbstractCreateService<Provider, Request_> {

	//Internal State ----------------------------------------------------

	@Autowired
	ProviderRequestRepository repository;


	@Override
	public boolean authorise(final Request<Request_> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Request_> request, final Request_ entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void unbind(final Request<Request_> request, final Request_ entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "description", "deadline", "reward");
		model.setAttribute("confirmation", false);
	}

	@Override
	public Request_ instantiate(final Request<Request_> request) {
		Request_ result;
		Date creationMoment;
		Date deadline = new Date(System.currentTimeMillis() + 100000);
		Double reward = 0.0;

		creationMoment = new Date(System.currentTimeMillis() - 1);

		result = new Request_();
		result.setCreationMoment(creationMoment);
		result.setDeadline(deadline);
		result.setReward(reward);

		return result;
	}

	@Override
	public void validate(final Request<Request_> request, final Request_ entity, final Errors errors) {
		assert request != null;
		assert entity != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "acme.validation.request.confirmation");

		assert errors != null;

	}

	@Override
	public void create(final Request<Request_> request, final Request_ entity) {

		this.repository.save(entity);
	}

}
