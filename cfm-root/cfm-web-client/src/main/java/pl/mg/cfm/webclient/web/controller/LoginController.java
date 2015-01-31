package pl.mg.cfm.webclient.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = { WebConstants.PARAM_EMPLOYEE })
public class LoginController {
    @RequestMapping(value = { WebConstants.URI_INDEX }, method = RequestMethod.GET)
    public String login(Model model, Principal principal) {

        return WebConstants.TEMPLATE_LOGIN;
    }

    @RequestMapping(value = { WebConstants.URI_LOGIN_ERROR }, method = RequestMethod.GET)
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return WebConstants.TEMPLATE_LOGIN;
    }

    @RequestMapping(value = { WebConstants.URI_LOGOUT }, method = RequestMethod.GET)
    public String logout(Model model) {
        model.addAttribute("logout", true);
        return WebConstants.TEMPLATE_LOGIN;
    }
}
