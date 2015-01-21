package pl.mg.cfm.webclient.web.controller;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.service.EmployeeService;
import pl.mg.cfm.webclient.web.domain.ErrorMessage;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes({ "error" })
public class UserDashboardController {

    Logger logger = Logger.getLogger(UserDashboardController.class);

    @Inject
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model, @ModelAttribute("error") ErrorMessage error, SessionStatus status) {

        logger.debug("user get");
        if (!model.containsAttribute("error")) {
            logger.debug("error is null");
            model.addAttribute("error", new ErrorMessage());
        }
        model.addAttribute("employee", new EmployeePojo());
        status.setComplete();
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
