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

import pl.mg.cfm.dao.exceptions.UserNotFoundException;
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
            UserNotFoundException {

        EmployeePojo employeePojo = employeeService.getEmployee(principal.getName());

        /*
         * String name = principal.getName(); logger.debug(name); Map modelMap =
         * model.asMap(); for (Object modelKey : modelMap.keySet()) { Object
         * modelValue = modelMap.get(modelKey); logger.debug(modelKey + " -- " +
         * modelValue); }
         */
        ModelAndView mav = new ModelAndView(WebConstants.TEMPLATE_HOME);
        mav.addObject(WebConstants.PARAM_EMPLOYEE, employeePojo);
        return mav;
    }
}
