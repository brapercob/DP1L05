
package acme.features.administrator.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {
	// Internal state ----------------------------

	@Autowired
	AdministratorChallengeRepository repository;


	// AbstractUpdateService<Administrator, Announcement> interface --------

	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "deadline", "goldGoal", "silverGoal", "bronzeGoal", "goldReward", "silverReward", "bronzeReward");
	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean validation1;
		boolean validation2;
		boolean validation3;
		boolean validation4;
		Double goldReward = request.getModel().getDouble("goldReward");
		Double silverReward = request.getModel().getDouble("silverReward");
		Double bronzeReward = request.getModel().getDouble("bronzeReward");

		validation1 = silverReward != null;
		validation2 = bronzeReward != null;
		errors.state(request, validation1, "silverReward", "javax.validation.constraints.NotNull.message");
		errors.state(request, validation2, "bronzeReward", "javax.validation.constraints.NotNull.message");
		validation3 = silverReward != null && goldReward != null && silverReward.compareTo(goldReward) < 0;
		validation4 = bronzeReward != null && silverReward != null && bronzeReward.compareTo(silverReward) < 0;

		errors.state(request, validation3, "silverReward", "acme.validation.challenge.silverReward");
		errors.state(request, validation4, "bronzeReward", "acme.validation.challenge.bronzeReward");
	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {

		this.repository.save(entity);
	}

}
