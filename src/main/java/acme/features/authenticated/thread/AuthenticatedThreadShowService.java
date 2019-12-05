
package acme.features.authenticated.thread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		boolean result = false;
		int threadId;
		Thread thread;
		Collection<Authenticated> participants;
		Principal principal;

		threadId = request.getModel().getInteger("id");
		thread = this.repository.findOneThreadById(threadId);
		participants = thread.getParticipants();
		principal = request.getPrincipal();
		for (Authenticated a : participants) {
			if (a.getUserAccount().getId() == principal.getAccountId()) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String userNames = "";
		Collection<Authenticated> participants = entity.getParticipants();
		for (Authenticated a : participants) {
			userNames = userNames.concat(a.getUserAccount().getUsername()).concat(" - ");
		}

		request.unbind(entity, model, "title", "moment");
		model.setAttribute("participants", userNames);

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

}
