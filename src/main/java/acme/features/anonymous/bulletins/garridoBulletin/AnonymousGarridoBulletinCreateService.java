
package acme.features.anonymous.bulletins.garridoBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GarridoBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousGarridoBulletinCreateService implements AbstractCreateService<Anonymous, GarridoBulletin> {

	@Autowired
	private AnonymousGarridoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GarridoBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<GarridoBulletin> request, final GarridoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<GarridoBulletin> request, final GarridoBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "quote");
	}

	@Override
	public GarridoBulletin instantiate(final Request<GarridoBulletin> request) {
		assert request != null;
		GarridoBulletin bulletin = new GarridoBulletin();
		bulletin.setName("");
		bulletin.setQuote("");
		bulletin.setMoment(new Date(System.currentTimeMillis() - 1));

		return bulletin;
	}

	@Override
	public void validate(final Request<GarridoBulletin> request, final GarridoBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<GarridoBulletin> request, final GarridoBulletin entity) {
		assert request != null;
		assert entity != null;

		entity.setMoment(new Date(System.currentTimeMillis() - 1));

		this.repository.save(entity);
	}

}
