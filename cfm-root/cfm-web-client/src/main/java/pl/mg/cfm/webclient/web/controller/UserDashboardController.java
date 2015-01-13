package pl.mg.cfm.webclient.web.controller;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.service.EmployeeService;

@Controller
@RequestMapping(value = "/user")
public class UserDashboardController {

    Logger logger = Logger.getLogger(UserDashboardController.class);

    @Inject
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model) {
        model.addAttribute("employee", new EmployeePojo());
        return "user_dashboard";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "login" })
    public String userDashboard(final EmployeePojo employee) {

        if (employee != null) {
            logger.debug(employee.getId());
        }
        return "user_dashboard";
    }
}
