package pl.mg.cfm.webclient.web.controller;

import javax.inject.Inject;
import javax.validation.Valid;

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

    @Inject
    private EmployeeService employeeService;

    private boolean error;
    private String errorMessage;

    @RequestMapping("error")
    public boolean getError() {
        return this.error;
    }

    @RequestMapping("errorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(@ModelAttribute("employee") final EmployeePojo employee) {
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "login" })
    public String login(@Valid @ModelAttribute("employee") EmployeePojo employee, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        System.out.println("id=" + employee.getId());
        System.out.println("pass=" + employee.getPassword());

        if (employeeService.login(employee.getId().toString(), employee.getPassword())){
            return "redirect:/user";
        }else{
            this.error = true;
            return "index";
        }

        

        // tu będzie redirect: aby nie móc wrócić
    }

}
