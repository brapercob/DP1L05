
package acme.features.authenticated.banner.commercial;

import acme.entities.banners.CommercialBanner;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/authenticated/commercial-banner/")
public class AuthenticatedCommercialBannerController extends AbstractController<Authenticated, CommercialBanner> {

	@Autowired
	private AuthenticatedCommercialBannerListService listService;

	@Autowired
	private AuthenticatedCommercialBannerShowService showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
