
package acme.features.authenticated.thread;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedThreadUpdateService implements AbstractUpdateService<Authenticated, Thread> {

	@Autowired
	private AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		boolean result = false;
		Principal principal;
		int threadId;
		Thread thread;

		threadId = request.getModel().getInteger("id");
		thread = this.repository.findOneThreadById(threadId);
		principal = request.getPrincipal();
		if (thread.getAuthenticated().getUserAccount().getId() == principal.getAccountId()) {
			result = true;
		}
		return result;
	}

	@Override
	public void bind(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "authenticated", "participants");

	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Authenticated> participants = entity.getParticipants();

		request.unbind(entity, model, "title");

		model.setAttribute("participants", participants);
		for (Authenticated au : participants) {
			Integer auId = au.getId();
			model.setAttribute(auId.toString(), true);
		}

	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		assert request != null;

		Thread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneThreadById(id);

		return result;

	}

	@Override
	public void validate(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAuthenticated;

		Principal principal = request.getPrincipal();
		Integer authId = principal.getActiveRoleId();
		boolean cB = request.getModel().getBoolean(authId.toString());

		isAuthenticated = cB == true;
		errors.state(request, isAuthenticated, authId.toString(), "acme.validation.thread.isAuthenticated");

		if (errors.hasErrors()) {
			Collection<Authenticated> participants = entity.getParticipants();
			request.getModel().setAttribute("participants", participants);
			for (Authenticated au : participants) {
				Integer auId = au.getId();
				request.getModel().setAttribute(auId.toString(), true);
			}
		}
	}

	@Override
	public void update(final Request<Thread> request, final Thread entity) {
		assert request != null;
		assert entity != null;

		Collection<Authenticated> participants = entity.getParticipants();
		Collection<Authenticated> res = new ArrayList<Authenticated>();
		for (Authenticated au : participants) {
			Integer auId = au.getId();
			if (request.getModel().getBoolean(auId.toString()) == true) {
				res.add(au);
			}
		}
		entity.setParticipants(res);
		this.repository.save(entity);

	}

}
