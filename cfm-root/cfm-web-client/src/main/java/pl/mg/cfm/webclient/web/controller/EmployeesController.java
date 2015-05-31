package pl.mg.cfm.webclient.web.controller;

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
import pl.mg.cfm.webclient.web.domain.EmployeeSearchCriteriaBean;

import javax.inject.Inject;
import java.security.Principal;

/**
 * Controller for class editing and searching employees
 */
@Controller
@RequestMapping(value = WebConstants.URI_EMPLOYEES)
@SessionAttributes({WebConstants.PARAM_EMPLOYEE})
public class EmployeesController {

    Logger logger = Logger.getLogger(EmployeesController.class);

    @Inject
    private EmployeeService employeeService;

    @ModelAttribute(WebConstants.PARAM_EMPLOYEE)
    public EmployeePojo populateMockEmployee() {
        return new EmployeePojo();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model, @ModelAttribute(WebConstants.PARAM_EMPLOYEE) EmployeePojo employee,
                               SessionStatus status, Principal principal, @ModelAttribute(WebConstants.PARAM_SEARCH_EMPLOYEE_CRITERIA)
                               EmployeeSearchCriteriaBean employeeSearchCriteria) throws NumberFormatException, EmployeeNotFoundException {
        logger.debug("/employees GET");

        if (employee == null || employee.getId() == null) {
            logger.debug("employee is null");
            employee = employeeService.getEmployee(principal.getName());
            model.addAttribute(WebConstants.PARAM_EMPLOYEE, employee);
        }
        return WebConstants.TEMPLATE_EMPLOYEES;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String userDashboard(final EmployeePojo employee, @ModelAttribute(WebConstants.PARAM_SEARCH_EMPLOYEE_CRITERIA) EmployeeSearchCriteriaBean employeeSearchCriteria) {


        logger.debug("/employees POST");
        return WebConstants.TEMPLATE_EMPLOYEES;
    }
}
