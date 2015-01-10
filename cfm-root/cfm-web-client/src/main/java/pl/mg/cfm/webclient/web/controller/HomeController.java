package pl.mg.cfm.webclient.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("welcome_message", "Dzien dobry");
        return mav;
    }
}
