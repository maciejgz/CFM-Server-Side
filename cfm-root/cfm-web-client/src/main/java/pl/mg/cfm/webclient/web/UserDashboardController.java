package pl.mg.cfm.webclient.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.mg.cfm.webclient.service.EmployeeService;

@Controller
@RequestMapping(value = "/user")
public class UserDashboardController {

    @Inject
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model) {
        return "user_dashboard";

    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView userDashboard() {
        ModelAndView mav = new ModelAndView("user_dashboard");
        return mav;
    }
}
