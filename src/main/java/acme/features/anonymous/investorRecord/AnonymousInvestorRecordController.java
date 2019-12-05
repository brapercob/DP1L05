
package acme.features.anonymous.investorRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investorRecords.InvestorRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/investor-record/")
public class AnonymousInvestorRecordController extends AbstractController<Anonymous, InvestorRecord> {

	@Autowired
	private AnonymousInvestorRecordListService			listService;

	@Autowired
	private AnonymousInvestorRecordListTopFiveService	listTopFiveService;

	@Autowired
	private AnonymousInvestorRecordShowService			showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addCustomCommand(CustomCommand.TOP, BasicCommand.LIST, this.listTopFiveService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
