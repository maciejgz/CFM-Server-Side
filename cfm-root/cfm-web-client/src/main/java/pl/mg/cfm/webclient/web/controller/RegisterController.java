package pl.mg.cfm.webclient.web.controller;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.mg.cfm.business.exception.InvalidInputDataException;
import pl.mg.cfm.dao.exceptions.RegisterUserException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.service.EmployeeService;

@Controller
@RequestMapping(value = "/register*")
public class RegisterController {

    Logger logger = Logger.getLogger(RegisterController.class);
    @Inject
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registerGet(Model model,
            @ModelAttribute(WebConstants.PARAM_EMPLOYEE) final EmployeePojo employee) {
        logger.debug("register GET");
        ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, params = "register")
    public ModelAndView registerUser(Model model, @ModelAttribute("employee") EmployeePojo employee)
            throws InvalidInputDataException, RegisterUserException {
        ModelAndView mav = new ModelAndView("register");
        logger.debug("register POST");
        employeeService.registerEmployee(employee.getFirstName(), employee.getLastName(), employee.getPassword());
        return mav;
    }
}
