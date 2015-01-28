package pl.mg.cfm.webclient.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String login() {
        return WebConstants.TEMPLATE_LOGIN;
    }

    @RequestMapping(value = { "/login-error" }, method = RequestMethod.GET)
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return WebConstants.TEMPLATE_LOGIN;
    }

    @RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
    public String logout(Model model) {
        model.addAttribute("logout", true);
        return WebConstants.TEMPLATE_LOGIN;
    }
}
