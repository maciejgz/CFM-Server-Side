package pl.mg.cfm.webclient.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.mg.cfm.webclient.springtest.AnnotatedBean;

@Controller
@RequestMapping(value = "/user")
public class UserDashboardController {

//    @Inject
//    private EmployeeService employeeService;
    
//    @Inject
//    private AnnotatedBean bean;

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model) {
        return "user_dashboard";

    }
}
