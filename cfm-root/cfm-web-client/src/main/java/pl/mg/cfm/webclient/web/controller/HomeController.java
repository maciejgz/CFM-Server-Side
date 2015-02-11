package pl.mg.cfm.webclient.web.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.service.EmployeeServiceImpl;

@Controller
@RequestMapping(value = WebConstants.URI_HOME)
@SessionAttributes(value = { WebConstants.PARAM_EMPLOYEE })
public class HomeController {

    Logger logger = Logger.getLogger(this.getClass());

    @Inject
    private EmployeeServiceImpl employeeService;

    @ModelAttribute(WebConstants.PARAM_EMPLOYEE)
    public EmployeePojo populateMockEmployee() {
        return new EmployeePojo();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homeGet(@ModelAttribute(WebConstants.PARAM_EMPLOYEE) EmployeePojo employee,
            HttpServletRequest request, Model model, Principal principal) throws NumberFormatException,
            EmployeeNotFoundException {
        logger.debug("/home GET");
        EmployeePojo employeePojo = employeeService.getEmployee(principal.getName());

        ModelAndView mav = new ModelAndView(WebConstants.TEMPLATE_HOME);
        mav.addObject(WebConstants.PARAM_EMPLOYEE, employeePojo);
        return mav;
    }
}
