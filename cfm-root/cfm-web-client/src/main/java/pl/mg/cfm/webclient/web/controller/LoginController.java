package pl.mg.cfm.webclient.web.controller;

import java.security.Principal;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes(value = { WebConstants.PARAM_EMPLOYEE })
public class LoginController {

    Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = { WebConstants.URI_INDEX }, method = RequestMethod.GET)
    public String login(Model model, Principal principal, RedirectAttributes redirectAttributes,
            @RequestParam(value = WebConstants.PARAM_REGISTERED_ID, required = false) String registeredId,
            @RequestParam(value = WebConstants.PARAM_REGISTER_COMPLETE, required = false) String registerComplete) {
        if (registeredId != null) {
            model.addAttribute(WebConstants.PARAM_REGISTERED_ID, registeredId);
        }

        if (registerComplete != null) {
            model.addAttribute(WebConstants.PARAM_REGISTER_COMPLETE, registerComplete);
        }
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
