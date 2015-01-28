package pl.mg.cfm.webclient.web.controller;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = WebConstants.URI_HOME)
public class HomeController {

    Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView homeGet() {
        ModelAndView mav = new ModelAndView(WebConstants.TEMPLATE_HOME);
        return mav;
    }
}
