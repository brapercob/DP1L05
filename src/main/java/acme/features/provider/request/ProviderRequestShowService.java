
package acme.features.provider.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.request.Request_;
import acme.entities.roles.Provider;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ProviderRequestShowService implements AbstractShowService<Provider, Request_> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ProviderRequestRepository repository;


	@Override
	public boolean authorise(final Request<Request_> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Request_> request, final Request_ entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "description", "creationMoment", "deadline", "reward");
	}

	@Override
	public Request_ findOne(final Request<Request_> request) {
		assert request != null;

		Request_ result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneRequestById(id);

		return result;
	}

}
