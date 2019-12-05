
package acme.features.anonymous.bulletins.brionesBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.BrionesBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousBrionesBulletinCreateService implements AbstractCreateService<Anonymous, BrionesBulletin> {

	@Autowired
	private AnonymousBrionesBulletinRepository repository;


	@Override
	public boolean authorise(final Request<BrionesBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<BrionesBulletin> request, final BrionesBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<BrionesBulletin> request, final BrionesBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "quote");
	}

	@Override
	public BrionesBulletin instantiate(final Request<BrionesBulletin> request) {
		assert request != null;
		BrionesBulletin bulletin = new BrionesBulletin();
		bulletin.setName("");
		bulletin.setQuote("");
		bulletin.setMoment(new Date(System.currentTimeMillis() - 1));

		return bulletin;
	}

	@Override
	public void validate(final Request<BrionesBulletin> request, final BrionesBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<BrionesBulletin> request, final BrionesBulletin entity) {
		assert request != null;
		assert entity != null;

		entity.setMoment(new Date(System.currentTimeMillis() - 1));

		this.repository.save(entity);
	}

}
