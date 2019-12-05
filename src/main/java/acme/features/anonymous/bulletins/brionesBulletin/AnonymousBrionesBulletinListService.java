
package acme.features.anonymous.bulletins.brionesBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.BrionesBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousBrionesBulletinListService implements AbstractListService<Anonymous, BrionesBulletin> {

	@Autowired
	private AnonymousBrionesBulletinRepository repository;


	@Override
	public boolean authorise(final Request<BrionesBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<BrionesBulletin> request, final BrionesBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "quote", "moment");
	}

	@Override
	public Collection<BrionesBulletin> findMany(final Request<BrionesBulletin> request) {
		assert request != null;

		return this.repository.findMany();
	}

}
