
package acme.features.administrator.customization;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomizationUpdateService implements AbstractUpdateService<Administrator, Customization> {

	@Autowired
	AdministratorCustomizationRepository repository;


	@Override
	public boolean authorise(final Request<Customization> request) {
		assert request != null;
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void bind(final Request<Customization> request, final Customization entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Customization> request, final Customization entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords", "threshold");

	}

	@Override
	public Customization findOne(final Request<Customization> request) {
		assert request != null;

		Customization result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Customization> request, final Customization entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isGreaterOrLess, isDefault = true;
		List<String> def = Arrays.asList("sex", "hardcore", "viagra", "cialis", "nigeria", "you've won", "you have won", "million dollar", "sexo,explícito", "ha ganado", "has ganado", "millón de dólares");
		for (String s : def) {
			if (!request.getModel().getString("spamWords").contains(s)) {
				isDefault = false;
			}
		}
		errors.state(request, isDefault, "spamWords", "acme.validation.customization.spamWords");

		isGreaterOrLess = request.getModel().getDouble("threshold") >= 0 && request.getModel().getDouble("threshold") <= 100;
		errors.state(request, isGreaterOrLess, "threshold", "acme.validation.customization.threshold");
	}

	@Override
	public void update(final Request<Customization> request, final Customization entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
