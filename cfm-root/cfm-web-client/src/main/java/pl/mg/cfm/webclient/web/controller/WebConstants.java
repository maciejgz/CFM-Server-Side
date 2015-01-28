package pl.mg.cfm.webclient.web.controller;

import org.springframework.web.servlet.ModelAndView;

public class WebConstants {

    // URIs
    public static final String URI_INDEX = "/";
    public static final String URI_USER = "/user";
    public static final String URI_HOME = "/home";

    // thymeleaf templates
    public static final String TEMPLATE_INDEX = "index";
    public static final String TEMPLATE_LOGIN = "login";
    public static final String TEMPLATE_REGISTER = "register";

    // model and session parameters names
    public static final String PARAM_ERROR = "error";
    public static final String PARAM_EMPLOYEE = "employee";
    public static final String PARAM_LOGOUT = "logout";
    public static final String PARAM_REGISTER_COMPLETE = "register_complete";
    public static final String PARAM_REGISTERED_ID = "registered_id";
    public static final String TEMPLATE_HOME = "home";

}
