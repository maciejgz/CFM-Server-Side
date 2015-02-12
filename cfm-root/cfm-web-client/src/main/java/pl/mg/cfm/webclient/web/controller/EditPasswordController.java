package pl.mg.cfm.webclient.web.controller;

import java.security.Principal;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.mg.cfm.business.exception.InvalidInputDataException;
import pl.mg.cfm.dao.exceptions.EmployeeNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.service.EmployeeService;
import pl.mg.cfm.webclient.web.domain.ChangePasswordBean;
import pl.mg.cfm.webclient.web.domain.ErrorMessage;

@Controller
@RequestMapping(value = WebConstants.URI_EMPLOYEE)
@SessionAttributes(value = { WebConstants.PARAM_EMPLOYEE })
public class EditPasswordController {

    private static Logger logger = Logger.getLogger(EditPasswordController.class);

    @Autowired
    EmployeeService employeeService;

    @ModelAttribute(WebConstants.PARAM_ERROR)
    public ErrorMessage populateMockError() {
        return new ErrorMessage();
    }

    @ModelAttribute(WebConstants.PARAM_EMPLOYEE)
    public EmployeePojo populateMockEmployee() {
        return new EmployeePojo();
    }

    @RequestMapping(method = RequestMethod.GET, value = WebConstants.URI_EDIT_PASSWORD)
    public String changePasswordGet(Model model, Principal principal,
            @ModelAttribute(WebConstants.PARAM_CHANGE_PASSWORD) ChangePasswordBean changePasswordBean,
            @ModelAttribute(WebConstants.PARAM_EMPLOYEE) EmployeePojo employee) {
        logger.debug("/employee/change_password GET");

        return WebConstants.TEMPLATE_CHANGE_PASSWORD;
    }

    @RequestMapping(method = RequestMethod.POST, value = WebConstants.URI_EDIT_PASSWORD, params = { WebConstants.POST_PARAM_UPDATE_PASSWORD })
    public String changePasswordPost(Model model, Principal principal,
            @ModelAttribute(WebConstants.PARAM_CHANGE_PASSWORD) ChangePasswordBean changePasswordBean,
            @ModelAttribute(WebConstants.PARAM_EMPLOYEE) EmployeePojo employee, HttpServletRequest request,
            final RedirectAttributes redirectAttributes) throws InvalidInputDataException, EmployeeNotFoundException {
        logger.debug("/employee/change_password POST");
        if (!changePasswordBean.getCurrentPassword().equals(employee.getPassword())) {
            throw new InvalidInputDataException();
        }
        employeeService.updatePassword(employee, changePasswordBean.getNewPassword(), changePasswordBean
                .getNewPasswordConfirm());
        redirectAttributes.addFlashAttribute(WebConstants.PARAM_UPDATE_SUCCESS, true);
        request.getSession().removeAttribute(WebConstants.PARAM_EMPLOYEE);
        model.addAttribute(WebConstants.PARAM_EMPLOYEE, employeeService.getEmployee(employee.getId().toString()));

        return WebConstants.URI_REDIRECT + WebConstants.URI_EMPLOYEE;
    }

    @ExceptionHandler(value = { InvalidInputDataException.class, EmployeeNotFoundException.class })
    public ModelAndView invalidInput(Exception e, HttpServletRequest request) {
        
        // TODO: separate error messages by error type
        logger.debug("errorType=" + e.getClass().toString());
        logger.debug("employee/edit/ ExceptionHandler: " + e.getClass().toString());
        ModelAndView mav = new ModelAndView();
        ErrorMessage error = new ErrorMessage(1, "Bledne dane");
        mav.addObject(WebConstants.PARAM_ERROR, error);
        mav.addObject(WebConstants.PARAM_EMPLOYEE, request.getSession().getAttribute(WebConstants.PARAM_EMPLOYEE));
        mav.addObject(WebConstants.PARAM_CHANGE_PASSWORD, new ChangePasswordBean());
        mav.setViewName(WebConstants.TEMPLATE_CHANGE_PASSWORD);
        return mav;
    }

}
