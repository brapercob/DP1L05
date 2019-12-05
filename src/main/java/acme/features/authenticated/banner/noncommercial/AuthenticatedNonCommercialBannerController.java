
package acme.features.authenticated.banner.noncommercial;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/authenticated/non-commercial-banner/")
public class AuthenticatedNonCommercialBannerController extends AbstractController<Authenticated, NonCommercialBanner> {

	@Autowired
	private AuthenticatedNonCommercialBannerListService		listService;

	@Autowired
	private AuthenticatedNonCommercialBannerShowService		showService;

	@PostConstruct
	private void initialize() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
