
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.entities.threads.Thread;
import acme.features.authenticated.thread.AuthenticatedThreadRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

import java.util.Date;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

    @Autowired
    private AuthenticatedMessageRepository repository;

    @Autowired
    private AuthenticatedThreadRepository threadRepository;


    @Override
    public boolean authorise(final Request<Message> request) {
        assert request != null;
        return true;
    }

    @Override
    public void bind(final Request<Message> request, final Message entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors, "moment");

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

    }

    @Override
    public void create(final Request<Message> request, final Message entity) {
        assert request != null;
        assert entity != null;
        entity.setMoment(entity.setMoment(new Date(System.currentTimeMillis() - 1)););
        this.repository.save(entity);

    }

}
