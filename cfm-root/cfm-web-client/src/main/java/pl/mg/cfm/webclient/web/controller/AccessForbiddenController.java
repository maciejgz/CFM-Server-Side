package pl.mg.cfm.webclient.web.controller;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Controller for 403 access-denied code
 * Created by m on 2015-04-30.
 */
@Controller
@SessionAttributes(value = {WebConstants.PARAM_EMPLOYEE})
public class AccessForbiddenController {


    Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = WebConstants.URI_ACCESS_FRBIDDEN, method = RequestMethod.GET)
    public String accessForbidden(Model model) {
        return WebConstants.TEMPLATE_ACCESS_FORBIDDEN;
    }

}
