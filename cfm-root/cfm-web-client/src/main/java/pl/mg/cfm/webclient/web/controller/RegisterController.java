package pl.mg.cfm.webclient.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.mg.cfm.domain.EmployeePojo;
import pl.mg.cfm.webclient.business.service.EmployeeService;

//@Controller
//@RequestMapping(value = "/register")
public class RegisterController {

    @Inject
    EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registerGet() {
        ModelAndView mav = new ModelAndView("register");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, params="login")
    public ModelAndView registerUser(final EmployeePojo employee) {
        
        ModelAndView mav = new ModelAndView();
        return mav;
    }
}
