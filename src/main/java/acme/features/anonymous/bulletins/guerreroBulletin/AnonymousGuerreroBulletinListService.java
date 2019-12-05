
package acme.features.anonymous.bulletins.guerreroBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.GuerreroBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousGuerreroBulletinListService implements AbstractListService<Anonymous, GuerreroBulletin> {

	@Autowired
	private AnonymousGuerreroBulletinRepository repository;


	@Override
	public boolean authorise(final Request<GuerreroBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<GuerreroBulletin> request, final GuerreroBulletin entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "quote", "moment");
	}

	@Override
	public Collection<GuerreroBulletin> findMany(final Request<GuerreroBulletin> request) {
		assert request != null;

		//Another option for this findMany is findAll
		return this.repository.findMany();
		//return null;
	}

}
