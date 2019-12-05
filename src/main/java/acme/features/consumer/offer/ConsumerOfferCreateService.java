
package acme.features.consumer.offer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {

		assert request != null;
		return true;

	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "text", "deadline", "rangeFrom", "rangeTo");
		model.setAttribute("confirmation", false);

	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		Offer result;

		result = new Offer();
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isEurosRangeTo, isEurosRangeFrom, confirmation, isRangeCorrect;

		isEurosRangeTo = request.getModel().getString("rangeTo").trim().startsWith("EUR") || request.getModel().getString("rangeTo").trim().endsWith("EUR") || request.getModel().getString("rangeTo").isEmpty();
		isEurosRangeFrom = request.getModel().getString("rangeFrom").trim().startsWith("EUR") || request.getModel().getString("rangeFrom").trim().endsWith("EUR") || request.getModel().getString("rangeFrom").isEmpty();
		errors.state(request, isEurosRangeTo, "rangeTo", "acme.validation.offer.euros-to");
		errors.state(request, isEurosRangeFrom, "rangeFrom", "acme.validation.offer.euros-from");

		String rangeToS = request.getModel().getString("rangeTo");
		String rangeFromS = request.getModel().getString("rangeFrom");
		Double rangeFrom = 0.0;
		Double rangeTo = 0.0;

		if (rangeToS.contains("EUR")) {
			if (request.getLocale().getDisplayLanguage().equals("Spanish")) {
				rangeToS = rangeToS.replace("EUR", "").replace(".", "").replace(",", ".").trim();
			} else {
				rangeToS = rangeToS.replace("EUR", "").replace(",", "").trim();
			}
			rangeTo = new Double(rangeToS);
		}

		if (rangeFromS.contains("EUR")) {
			if (request.getLocale().getDisplayLanguage().equals("Spanish")) {
				rangeFromS = rangeFromS.replace("EUR", "").replace(".", "").replace(",", ".").trim();
			} else {
				rangeFromS = rangeFromS.replace("EUR", "").replace(",", "").trim();
			}
			rangeFrom = new Double(rangeFromS);
		}

		isRangeCorrect = rangeFrom <= rangeTo;
		errors.state(request, isRangeCorrect, "rangeTo", "acme.validation.offer.rangeTo.correct");

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "acme.validation.offer.confirmation");

	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
