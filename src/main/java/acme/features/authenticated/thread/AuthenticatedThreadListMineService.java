
package acme.features.authenticated.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedThreadListMineService implements AbstractListService<Authenticated, Thread> {

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;
		return true;

	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		String userNames = "";
		Collection<Authenticated> participants = entity.getParticipants();
		for (Authenticated a : participants) {
			userNames = userNames.concat(a.getUserAccount().getUsername()).concat(", ");
		}
		request.unbind(entity, model, "moment", "title");
		model.setAttribute("participants", userNames);
	}

	@Override
	public Collection<Thread> findMany(final Request<Thread> request) {
		assert request != null;

		Collection<Thread> threads;
		Collection<Thread> result;
		Principal principal;

		principal = request.getPrincipal();
		threads = this.repository.findMany();
		result = this.repository.findMany();
		for (Thread t : threads) {
			List<Integer> ids = new ArrayList<Integer>();
			for (Authenticated a : t.getParticipants()) {
				ids.add(a.getId());
			}
			if (!ids.contains(principal.getActiveRoleId())) {
				result.remove(t);
			}
		}
		return result;
	}
}
