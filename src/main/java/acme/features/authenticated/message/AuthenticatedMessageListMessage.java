
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.entities.threads.Thread;
import acme.features.authenticated.thread.AuthenticatedThreadRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListMessage implements AbstractListService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository	repository;

	@Autowired
	AuthenticatedThreadRepository	threadRepository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result = false;
		int threadId;
		Thread thread;
		Principal principal;

		principal = request.getPrincipal();
		threadId = request.getModel().getInteger("threadId");
		thread = this.threadRepository.findOneThreadById(threadId);
		for (Authenticated a : thread.getParticipants()) {
			if (a.getUserAccount().getId() == principal.getAccountId()) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags");

	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		int threadId;
		Collection<Message> result;

		threadId = request.getModel().getInteger("threadId");
		result = this.repository.findManyByThreadId(threadId);

		return result;
	}

}
