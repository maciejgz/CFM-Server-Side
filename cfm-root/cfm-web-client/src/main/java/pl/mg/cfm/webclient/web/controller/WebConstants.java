package pl.mg.cfm.webclient.web.controller;

public class WebConstants {

    // URIs
    public static final String URI_INDEX = "/";
    public static final String URI_EMPLOYEE = "/employee";
    public static final String URI_HOME = "/home";
    public static final String URI_DATA = "/employee";
    public static final String URI_EMPLOYEES = "/employees";
    public static final String URI_CARS = "/cars";
    public static final String URI_LOGOUT = "/logout";
    public static final String URI_LOGIN_ERROR = "/login-error";
    public static final String URI_REGISTER = "/register";
    public static final String URI_EDIT = "/edit";
    public static final String URI_REDIRECT = "redirect:";

    // thymeleaf templates
    public static final String TEMPLATE_INDEX = "index";
    public static final String TEMPLATE_LOGIN = "login";
    public static final String TEMPLATE_REGISTER = "register";
    public static final String TEMPLATE_HOME = "home";
    public static final String TEMPLATE_EMPLOYEE = "employee";
    public static final String TEMPLATE_EMPLOYEES = "employees";
    public static final String TEMPLATE_CARS = "cars";
    public static final String TEMPLATE_EMPLOYEE_EDIT = "employee_edit";
    

    // model and session parameters names
    public static final String PARAM_ERROR = "error";
    public static final String PARAM_EMPLOYEE = "employee";
    public static final String PARAM_LOGOUT = "logout";
    public static final String PARAM_REGISTER_COMPLETE = "register_complete";
    public static final String PARAM_REGISTERED_ID = "registered_id";
    public static final String PARAM_EMPLOYEE_TO_EDIT = "employee_to_edit";
    public static final String PARAM_UPDATE_SUCCESS = "update_success";

    // POST PARAMS
    public static final String POST_PARAM_REGISTER = "register";
    public static final String POST_PARAM_UPDATE_DATA = "update_data";
    public static final String POST_PARAM_UPDATE_PASSWORD = "update_password";

}
