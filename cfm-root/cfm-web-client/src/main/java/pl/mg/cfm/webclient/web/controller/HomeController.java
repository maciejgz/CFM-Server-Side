package pl.mg.cfm.webclient.web.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.service.EmployeeService;

@Controller
@RequestMapping("/")
public class HomeController {

    Logger logger = Logger.getLogger(HomeController.class);

    @Inject
    private EmployeeService employeeService;

    private boolean error = false;
    private String errorMessage;

    @ModelAttribute("error")
    public boolean getError() {
        return this.error;
    }

    @ModelAttribute("errorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(@ModelAttribute("employee") final EmployeePojo employee) {
        logger.debug("/ GET");
        logger.debug("error=" + error);
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "login" })
    public String login(@Valid @ModelAttribute("employee") EmployeePojo employee, BindingResult bindingResult,
            Model model) {
        logger.debug("/ POST");
        logger.debug("error=" + error);
        if (bindingResult.hasErrors()) {
            logger.debug("POST has errors");
            this.error = true;
            this.errorMessage = "Nieprawidłowy login lub haslo";
            return "index";
        }

        System.out.println("id=" + employee.getId());
        System.out.println("pass=" + employee.getPassword());

        if (employeeService.login(employee.getId().toString(), employee.getPassword())) {
            return "redirect:/user";
        } else {
            this.error = true;
            this.errorMessage = "Nieprawidłowy login lub haslo";
            return "index";
        }

        // tu będzie redirect: aby nie móc wrócić
    }

}
