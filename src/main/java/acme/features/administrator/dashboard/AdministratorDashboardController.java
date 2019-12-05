package acme.features.administrator.dashboard;

import acme.forms.Dashboard;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/administrator/dashboard/")
public class AdministratorDashboardController extends AbstractController<Administrator, Dashboard> {

    @Autowired
    private AdministratorDashboardShowService show;

    @PostConstruct
    private void initialise() {
        super.addBasicCommand(BasicCommand.SHOW, show);
    }
}
