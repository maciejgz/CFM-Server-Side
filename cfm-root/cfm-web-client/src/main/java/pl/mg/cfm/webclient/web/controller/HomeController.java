package pl.mg.cfm.webclient.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pl.mg.cfm.dao.exceptions.InvalidPasswordException;
import pl.mg.cfm.dao.exceptions.UserNotFoundException;
import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.service.EmployeeService;
import pl.mg.cfm.webclient.web.domain.ErrorMessage;

@Controller
@RequestMapping("/")
@SessionAttributes({ WebConstants.PARAM_ERROR })
public class HomeController {

    Logger logger = Logger.getLogger(HomeController.class);

    @Inject
    private EmployeeService employeeService;

    /**
     * obiekt potrzebny do wstrzyknięcia domyślnego parametru do sesji, poniewaz
     * za pierwszym razem nie jest on jeszcze ustawiony i sypie bledami.
     * 
     * @return
     */
    @ModelAttribute(WebConstants.PARAM_ERROR)
    public ErrorMessage populateMockError() {
        return new ErrorMessage();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(@ModelAttribute(WebConstants.PARAM_EMPLOYEE) final EmployeePojo employee,
            @ModelAttribute(WebConstants.PARAM_ERROR) ErrorMessage error, Model model) {
        logger.debug("/ GET");

        // wstawianie domyślnego error potrzebnego do sparsowania strony.
        if (error == null) {
            logger.debug("add mock error to model");
            ErrorMessage mockError = new ErrorMessage();
            model.addAttribute(WebConstants.PARAM_ERROR, mockError);
        }
        return WebConstants.TEMPLATE_INDEX;
    }

    @RequestMapping(method = RequestMethod.POST, params = { "login" })
    public String login(@Valid @ModelAttribute(WebConstants.PARAM_EMPLOYEE) EmployeePojo employee,
            @ModelAttribute(WebConstants.PARAM_ERROR) ErrorMessage error, BindingResult bindingResult, Model model,
            SessionStatus session) throws NumberFormatException, UserNotFoundException, InvalidPasswordException {
        logger.debug("/ POST");

        /*
         * logger.debug("error=" + error); if (bindingResult.hasErrors()) {
         * logger.debug("POST has errors"); error.setErrorCode(1);
         * error.setErrorMessage("Nieprawidłowy login lub haslo"); return
         * "index"; }
         */
        System.out.println("id=" + employee.getId());
        System.out.println("pass=" + employee.getPassword());

        if (employeeService.login(employee.getId().toString(), employee.getPassword())) {
            EmployeePojo employeeModel = employeeService.getEmployee(employee.getId().toString());
            error = new ErrorMessage();
            return "redirect:/user";
        } else {
            throw new UserNotFoundException();
        }

    }

    @ExceptionHandler(value = NumberFormatException.class)
    public String catchNumberFormatException(@ModelAttribute(WebConstants.PARAM_ERROR) ErrorMessage error,
            final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        error = new ErrorMessage(1, "nieprawidlowy format danych");
        mav.addObject(WebConstants.PARAM_ERROR, error);
        return WebConstants.TEMPLATE_INDEX;
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public String catchUserNotFoundException(@ModelAttribute(WebConstants.PARAM_ERROR) ErrorMessage error,
            final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        error = new ErrorMessage(2, "nieprawidlowy uzytkownik lub haslo");
        mav.addObject(WebConstants.PARAM_ERROR, error);
        return WebConstants.TEMPLATE_INDEX;
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    public String catchInvalidPasswordException(@ModelAttribute(WebConstants.PARAM_ERROR) ErrorMessage error,
            final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();

        error = new ErrorMessage(2, "nieprawidlowy uzytkownik lub haslo");
        mav.addObject(WebConstants.PARAM_ERROR, error);
        return WebConstants.TEMPLATE_INDEX;
    }

}
