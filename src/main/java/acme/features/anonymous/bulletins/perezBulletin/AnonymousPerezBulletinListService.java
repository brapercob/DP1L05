
package acme.features.anonymous.bulletins.perezBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.PerezBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousPerezBulletinListService implements AbstractListService<Anonymous, PerezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousPerezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<PerezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<PerezBulletin> request, final PerezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "content", "moment");
	}

	@Override
	public Collection<PerezBulletin> findMany(final Request<PerezBulletin> request) {
		assert request != null;

		Collection<PerezBulletin> result;

		result = this.repository.findMany();

		return result;
	}

}
