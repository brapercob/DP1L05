
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.messages.Message;
import acme.entities.threads.Thread;
import acme.features.administrator.customization.AdministratorCustomizationRepository;
import acme.features.authenticated.thread.AuthenticatedThreadRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	private AuthenticatedMessageRepository			repository;

	@Autowired
	private AuthenticatedThreadRepository			threadRepository;

	@Autowired
	private AdministratorCustomizationRepository	customizationRepository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result = false;
		Principal principal;
		int threadId;
		Thread thread;

		threadId = request.getModel().getInteger("threadId");
		thread = this.threadRepository.findOneThreadById(threadId);
		principal = request.getPrincipal();
		Collection<Authenticated> participants = thread.getParticipants();
		for (Authenticated au : participants) {
			if (au.getUserAccount().getId() == principal.getAccountId()) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "thread");

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags", "body");
		model.setAttribute("confirmation", false);
		model.setAttribute("threadId", entity.getThread().getId());
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;
		Message result;
		Integer threadId;
		Thread thread;

		result = new Message();
		threadId = request.getModel().getInteger("threadId");
		thread = this.threadRepository.findOneThreadById(threadId);
		result.setThread(thread);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation, isSpam;

		Collection<Customization> c = this.customizationRepository.findCustomization();
		String spamWords = "";
		Double threshold = 1.;
		Double spamCount = 0.;
		String sW = "";
		String body = request.getModel().getString("body").toUpperCase();

		for (Customization cus : c) {
			spamWords = cus.getSpamWords();
			threshold = cus.getThreshold();
		}
		List<String> spamList = Arrays.asList(spamWords.split(","));

		for (String s : spamList) {
			if (body.contains(s.toUpperCase())) {
				spamCount += 1.;
				sW = sW.concat(s + ",");
			}
		}

		isSpam = spamCount < threshold;
		errors.state(request, isSpam, "body", "acme.validation.message.spam", sW);

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "acme.validation.message.confirmation");

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;
		entity.setMoment(new Date(System.currentTimeMillis() - 1));
		this.repository.save(entity);

	}

}
