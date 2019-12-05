
package acme.features.anonymous.bulletins.garridoBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GarridoBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousGarridoBulletinListService implements AbstractListService<Anonymous, GarridoBulletin> {

	@Autowired
	private AnonymousGarridoBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GarridoBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<GarridoBulletin> request, final GarridoBulletin entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "quote", "moment");
	}

	@Override
	public Collection<GarridoBulletin> findMany(final Request<GarridoBulletin> request) {
		assert request != null;

		//Another option for this findMany is findAll
		return this.repository.findMany();
		//return null;
	}

}
