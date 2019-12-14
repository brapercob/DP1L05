/*
 * AuthenticatedSponsorUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.sponsor;

import acme.entities.roles.Sponsor;
import acme.framework.components.*;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class AuthenticatedSponsorUpdateService implements AbstractUpdateService<Authenticated, Sponsor> {

    // Internal state ---------------------------------------------------------

    @Autowired
    private AuthenticatedSponsorRepository repository;


    // AbstractUpdateService<Authenticated, Sponsor> interface -----------------

    @Override
    public boolean authorise(final Request<Sponsor> request) {
        assert request != null;

        return true;
    }

    @Override
    public void validate(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        Calendar actualDate = new GregorianCalendar();
        Calendar ccDate = new GregorianCalendar();
        ccDate.set(Calendar.MONTH, entity.getExpirationMonth() - 1);
        ccDate.set(Calendar.YEAR, entity.getExpirationYear());
        boolean yearDates = actualDate.get(Calendar.YEAR) <= entity.getExpirationYear();
        errors.state(request, yearDates, "expirationYear", "error.exp.month");
        boolean dates = actualDate.get(Calendar.YEAR) == ccDate.get(Calendar.YEAR) && (ccDate.get(Calendar.MONTH) + 1) < actualDate.get(Calendar.MONTH) + 1;
        errors.state(request, !dates, "expirationMonth", "error.exp.cc.month");
        errors.state(request, !dates, "expirationYear", "error.exp.cc.month");

    }

    @Override
    public void bind(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors);
    }

    @Override
    public void unbind(final Request<Sponsor> request, final Sponsor entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "organizationName", "creditCardNumber", "holder", "brand", "expirationMonth", "expirationYear");
    }

    @Override
    public Sponsor findOne(final Request<Sponsor> request) {
        assert request != null;

        Sponsor result;
        Principal principal;
        int userAccountId;

        principal = request.getPrincipal();
        userAccountId = principal.getAccountId();

        result = this.repository.findOneSponsorByUserAccountId(userAccountId);

        return result;
    }

    @Override
    public void update(final Request<Sponsor> request, final Sponsor entity) {
        assert request != null;
        assert entity != null;

        this.repository.save(entity);
    }

    @Override
    public void onSuccess(final Request<Sponsor> request, final Response<Sponsor> response) {
        assert request != null;
        assert response != null;

        if (request.isMethod(HttpMethod.POST)) {
            PrincipalHelper.handleUpdate();
        }
    }

}
