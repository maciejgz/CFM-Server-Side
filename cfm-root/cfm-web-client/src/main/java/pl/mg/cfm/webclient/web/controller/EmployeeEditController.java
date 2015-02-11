package pl.mg.cfm.webclient.web.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
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
import pl.mg.cfm.webclient.web.domain.ErrorMessage;

/**
 * Potrzebne jest rozdzielenie kontrolerów do obsługi błędów.
 * @author m
 *
 */
@Controller
@RequestMapping(value = WebConstants.URI_EMPLOYEE)
@SessionAttributes({ WebConstants.PARAM_EMPLOYEE })
public class EmployeeEditController {

    Logger logger = Logger.getLogger(EmployeeEditController.class);

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

    @RequestMapping(method = RequestMethod.GET, value = WebConstants.URI_EDIT)
    public String editEmployeeDataGet(Model model, Principal principal,
            @ModelAttribute(WebConstants.PARAM_EMPLOYEE) EmployeePojo employee) throws NumberFormatException,
            EmployeeNotFoundException {
        logger.debug("employee/edit/ GET");
        EmployeePojo employeeToEditEmployeePojo = new EmployeePojo(employee);
        model.addAttribute(WebConstants.PARAM_EMPLOYEE_TO_EDIT, employeeToEditEmployeePojo);

        return WebConstants.TEMPLATE_EMPLOYEE_EDIT;
    }

    @RequestMapping(method = RequestMethod.POST, value = WebConstants.URI_EDIT, params = { WebConstants.POST_PARAM_UPDATE_DATA })
    public String editEmployeeDataPost(Model model, Principal principal,
            @ModelAttribute(WebConstants.PARAM_EMPLOYEE_TO_EDIT) EmployeePojo employeeToEdit,
            HttpServletRequest request, final RedirectAttributes redirectAttributes) throws NumberFormatException,
            EmployeeNotFoundException, InvalidInputDataException {
        logger.debug("employee/edit/ POST");
        EmployeePojo sessionEmployee = (EmployeePojo) request.getSession().getAttribute(WebConstants.PARAM_EMPLOYEE);

        employeeService.updateEmployee(sessionEmployee.getId(), employeeToEdit.getFirstName(), employeeToEdit
                .getLastName(), sessionEmployee.getPassword());

        redirectAttributes.addFlashAttribute(WebConstants.PARAM_UPDATE_SUCCESS, true);
        //        logger.debug("newEmployeeinController=" + employeeService.getEmployee(sessionEmployee.getId().toString()));
        request.getSession().removeAttribute(WebConstants.PARAM_EMPLOYEE);
        model.addAttribute(WebConstants.PARAM_EMPLOYEE, employeeService.getEmployee(sessionEmployee.getId().toString()));
        return WebConstants.URI_REDIRECT + WebConstants.URI_EMPLOYEE;
    }

    @ExceptionHandler(value = { InvalidInputDataException.class, NumberFormatException.class })
    public ModelAndView invalidInput(Exception e, HttpServletRequest request) {
        logger.debug("employee/edit/ ExceptionHandler: " + e.getClass().toString());
        ModelAndView mav = new ModelAndView();

        ErrorMessage error = new ErrorMessage(1, "nieprawidlowy format danych");
        mav.addObject(WebConstants.PARAM_ERROR, error);
        mav.addObject(WebConstants.PARAM_EMPLOYEE_TO_EDIT, request.getSession().getAttribute(
                WebConstants.PARAM_EMPLOYEE));
        mav.addObject(WebConstants.PARAM_EMPLOYEE, request.getSession().getAttribute(WebConstants.PARAM_EMPLOYEE));
        mav.setViewName(WebConstants.TEMPLATE_EMPLOYEE_EDIT);
        return mav;
    }
}
