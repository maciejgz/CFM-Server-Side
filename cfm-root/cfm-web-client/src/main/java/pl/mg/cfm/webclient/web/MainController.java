package pl.mg.cfm.webclient.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(Model model) {

        model.addAttribute("welcome.message", "Dzien dobry");
        return "index";
    }
}
