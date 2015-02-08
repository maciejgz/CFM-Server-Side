package pl.mg.cfm.webclient.web.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
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

    @ModelAttribute(WebConstants.PARAM_ERROR)
    public ErrorMessage populateMockError() {
        return new ErrorMessage();
    }

    @ModelAttribute(WebConstants.PARAM_EMPLOYEE)
    public EmployeePojo populateMockEmployee() {
        return new EmployeePojo();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model, @ModelAttribute(WebConstants.PARAM_EMPLOYEE) EmployeePojo employee,
            SessionStatus status, Principal principal) throws NumberFormatException, EmployeeNotFoundException {
        logger.debug("/employee GET");
        addSessionEmployeeIfNull(employee, model, principal);

        return WebConstants.TEMPLATE_EMPLOYEE;
    }

    private void addSessionEmployeeIfNull(EmployeePojo employeePojo, Model model, Principal principal)
            throws NumberFormatException, EmployeeNotFoundException {
        if (employeePojo == null || employeePojo.getId() == null) {
            logger.debug("employee is null");
            EmployeePojo newEmployeePojo = employeeService.getEmployee(principal.getName());
            newEmployeePojo.setId(newEmployeePojo.getId());
            newEmployeePojo.setFirstName(newEmployeePojo.getFirstName());
            newEmployeePojo.setLastName(newEmployeePojo.getLastName());
            newEmployeePojo.setPassword(newEmployeePojo.getPassword());
            model.addAttribute(WebConstants.PARAM_EMPLOYEE, newEmployeePojo);
        }
    }
}
