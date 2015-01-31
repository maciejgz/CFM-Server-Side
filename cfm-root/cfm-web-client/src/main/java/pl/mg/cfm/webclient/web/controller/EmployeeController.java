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
@RequestMapping(value = WebConstants.URI_EMPLOYEE)
@SessionAttributes({ WebConstants.PARAM_EMPLOYEE })
public class EmployeeController {

    Logger logger = Logger.getLogger(EmployeeController.class);

    @Inject
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model, @ModelAttribute(WebConstants.PARAM_ERROR) ErrorMessage error,
            @ModelAttribute(WebConstants.PARAM_EMPLOYEE) EmployeePojo employee, SessionStatus status) {

        logger.debug("/user GET");

        if (!(employee == null)) {
            logger.debug(employee);
        }

        logger.debug("user get");
        if (error == null) {
            logger.debug("error is null");
            model.addAttribute("error", new ErrorMessage());
        }
        model.addAttribute("employee", new EmployeePojo());
        status.setComplete();
        return "user_dashboard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String userDashboard(final EmployeePojo employee) {

        logger.debug("/user GET");
        if (employee != null) {
            logger.debug(employee.getId());
        }
        return "user_dashboard";
    }
}
