
package acme.features.sponsor.banner.nonCommercial;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorNonCommercialBannerCreateService implements AbstractCreateService<Sponsor, NonCommercialBanner> {

    @Autowired
    private SponsorNonCommercialBannerRepository repository;


    @Override
    public boolean authorise(final Request<NonCommercialBanner> request) {
        assert request != null;

        return true;
    }

    @Override
    public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors);

    }

    @Override
    public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "picture", "slogan", "targetURL", "jingle");

    }

    @Override
    public NonCommercialBanner instantiate(final Request<NonCommercialBanner> request) {
        NonCommercialBanner ncb = new NonCommercialBanner();
        Sponsor sponsor = this.repository.findSponsor(request.getPrincipal().getAccountId() + 1);
        ncb.setJingle("");
        ncb.setPicture("");
        ncb.setSlogan("");
        ncb.setTargetURL("");
        ncb.setSponsor(sponsor);

        return ncb;
    }

    @Override
    public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

    }

    @Override
    public void create(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
        assert request != null;
        assert entity != null;

        this.repository.save(entity);
    }

}
