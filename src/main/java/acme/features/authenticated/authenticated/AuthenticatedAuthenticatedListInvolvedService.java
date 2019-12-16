
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuthenticatedListInvolvedService implements AbstractListService<Authenticated, Authenticated> {

	@Autowired
	AuthenticatedAuthenticatedRepository repository;


	@Override
	public boolean authorise(final Request<Authenticated> request) {
		assert request != null;
		return true;

	}

	@Override
	public void unbind(final Request<Authenticated> request, final Authenticated entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username");
		model.setAttribute("identity.name", entity.getIdentity().getName());
		model.setAttribute("identity.surname", entity.getIdentity().getSurname());
		model.setAttribute("identity.email", entity.getIdentity().getEmail());

	}

	@Override
	public Collection<Authenticated> findMany(final Request<Authenticated> request) {
		assert request != null;
		Collection<Authenticated> result;

		int threadId;

		threadId = request.getModel().getInteger("threadId");
		result = this.repository.findAuthenticatedByThreadId(threadId);

		return result;
	}
}
