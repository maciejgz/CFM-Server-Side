package pl.mg.cfm.webclient.web.controller;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.mg.cfm.business.exception.InvalidInputDataException;
import pl.mg.cfm.dao.exceptions.RegisterUserException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.service.EmployeeService;
import pl.mg.cfm.webclient.web.domain.ErrorMessage;

@Controller
@RequestMapping(value = "/register*")
public class RegisterController {

    Logger logger = Logger.getLogger(RegisterController.class);
    @Inject
    EmployeeService employeeService;

    @ModelAttribute(WebConstants.PARAM_ERROR)
    public ErrorMessage populateMockError() {
        return new ErrorMessage();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registerGet(Model model,
            @ModelAttribute(WebConstants.PARAM_EMPLOYEE) final EmployeePojo employee) {
        logger.debug("register GET");
        ModelAndView mav = new ModelAndView(WebConstants.TEMPLATE_REGISTER);

        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, params = "register")
    public ModelAndView registerUser(Model model, @ModelAttribute(WebConstants.PARAM_EMPLOYEE) EmployeePojo employee)
            throws InvalidInputDataException, RegisterUserException {
        ModelAndView mav = new ModelAndView(WebConstants.TEMPLATE_LOGIN);
        logger.debug("register POST");
        int registeredId = employeeService.registerEmployee(employee.getFirstName(), employee.getLastName(), employee.getPassword());
        employee = new EmployeePojo();
        mav.addObject(WebConstants.PARAM_EMPLOYEE, new EmployeePojo());
        mav.addObject(WebConstants.PARAM_REGISTER_COMPLETE, true);
        mav.addObject(WebConstants.PARAM_REGISTERED_ID,registeredId);
        return mav;
    }

    @ExceptionHandler(value = InvalidInputDataException.class)
    public ModelAndView invalidInput() {
        ModelAndView mav = new ModelAndView();
        ErrorMessage error = new ErrorMessage(1, "nieprawidlowy format danych");
        mav.addObject(WebConstants.PARAM_ERROR, error);
        mav.addObject(WebConstants.PARAM_EMPLOYEE, new EmployeePojo());
        mav.setViewName(WebConstants.TEMPLATE_REGISTER);
        return mav;
    }

}
