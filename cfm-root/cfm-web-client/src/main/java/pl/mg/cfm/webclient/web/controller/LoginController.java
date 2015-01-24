package pl.mg.cfm.webclient.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class LoginController {
    @RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = { "/login-error" }, method = RequestMethod.GET)
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
