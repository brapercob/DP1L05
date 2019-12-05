
package acme.features.sponsor.banner.nonCommercial;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/sponsor/non-commercial-banner/")
public class SponsorNonCommercialBannerController extends AbstractController<Sponsor, NonCommercialBanner> {

//	@Autowired
//	private SponsorNonCommercialBannerCreateService createService;
//
//	@Autowired
//	private SponsorNonCommercialBannerUpdateService updateService;
//
//	@Autowired
//	private SponsorNonCommercialBannerDeleteService deleteService;

	@Autowired
	private SponsorNonCommercialBannerListService listService;

	@Autowired
	private SponsorNonCommercialBannerShowService showService;

	@PostConstruct
	private void initialize() {
//		super.addBasicCommand(BasicCommand.CREATE, this.createService);
//		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
//		super.addBasicCommand(BasicCommand.DELETE, deleteService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
