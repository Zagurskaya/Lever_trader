package com.gmail.zagurskaya.web.constant;

public class URLConstant {
    public static final String URL_CASH = "/cash";
    public static final String URL_ADMIN = "/admin";
    public static final String URL_CONTROLLER = "/controller";
    public static final String URL_SECURE_REST_API = "SecureRestApi";
    public static final String URL_403 = "/403";
    public static final String URL_LOGIN = "/login";
    public static final String URL_REST_API = "/api/**";
    public static final String URL_USERS = "/users";

    public static final String URL_ADMINISTRATOR_NEW_USERS = "/new_user";
    public static final String URL_ADMINISTRATOR_NEW_USERS_POST = "/users/new";
    public static final String URL_ADMINISTRATOR_USERS_DELETE = "/users/delete";
    public static final String URL_ADMINISTRATOR_UPDATE_ROLE = "/users/update_role";
    public static final String URL_ADMINISTRATOR_UPDATE_PASSWORD = "/users/update_password";
    public static final String URL_ADMINISTRATOR_Comment = "/Comment";
    public static final String URL_ADMINISTRATOR_Comment_DELETE = "/Comment/delete";
    public static final String URL_ADMINISTRATOR_EXIT = "/exit";

    public static final String URL_CASH_CURRENCY = "/cash/currency";
    public static final String URL_CASH_ALL_CURRENCIES = "/all_currencies";
    public static final String URL_CASH_RATENB = "/ratenb";
    public static final String URL_CASH_RATECB = "/ratecb";

    public static final String URL_CASH_DUTIES = "/cash/duties";
    public static final String URL_CASH_DUTIES_OPEN = "/open_duties";
    public static final String URL_CASH_DUTIES_CLOSE = "/close_duties";

    public static final String URL_CASH_OPERATIONS = "/cash/operation";
    public static final String URL_CASH_OPERATIONS_PAYMENTS = "/payments";
    public static final String URL_CASH_PAYMENTS10 = "/payment10";
    public static final String URL_CASH_PAYMENTS20 = "/payment20";
    public static final String URL_CASH_PAYMENTS998 = "/payment998";
    public static final String URL_CASH_USER_OPERATION = "/user_operation";

    public static final String URL_CONTROLLER_ALL_CURRENCIES = "/all_currencies";
    public static final String URL_CONTROLLER_RATENB = "/ratenb";
    public static final String URL_CONTROLLER_RATECB = "/ratecb";
    public static final String URL_CONTROLLER_ENTRIES = "/entries";

    public static final String PATH_ADMINISTRATOR_USERS = "administrator/users";
    public static final String PATH_ADMINISTRATOR_ADMIN = "administrator/admin";
    public static final String PATH_ADMINISTRATOR_NEW_USERS = "administrator/new_user";
    public static final String PATH_ADMINISTRATOR_NEW_USERS_POST = "redirect:/admin/users";
    public static final String PATH_ADMINISTRATOR_Comment = "administrator/Comment";
    public static final String PATH_ADMINISTRATOR_Comment_REDIRECT = "redirect:/admin/Comment";

    public static final String PATH_CASH_HOME = "cash/home_cash";
    public static final String PAYH_CASH_ALL_CURRENCIES = "cash/currency/all_currencies";
    public static final String PATH_CASH_RATENB = "cash/currency/ratenb";
    public static final String PATH_CASH_RATECB = "cash/currency/ratecb";

    public static final String PATH_CASH_DUTIES = "/cash/duties";
    public static final String PATH_CASH_DUTIES_REDIRECT = "redirect:/cash/duties";

    public static final String PATH_CASH_OPERATIONS_PAYMENTS = "cash/operation/payments";
    public static final String PATH_CASH_OPERATIONS_PAYMENTS_POST = "redirect:payments";
    public static final String PATH_CASH_PAYMENTS10_GET = "cash/operation/payment10";
    public static final String PATH_CASH_PAYMENTS10_POST = "cash/operation/check10";
    public static final String PATH_CASH_PAYMENTS20_GET = "cash/operation/payment20";
    public static final String PATH_CASH_PAYMENTS20_POST = "cash/operation/check20";
    public static final String PATH_CASH_PAYMENTS998_GET = "cash/operation/payment20";
    public static final String PATH_CASH_PAYMENTS998_POST = "cash/operation/check20";
    public static final String PATH_CASH_USER_OPERATION = "cash/operation/user_operation";


    public static final String PATH_CONTROLLER = "controller/home_controller";
    public static final String PAYH_CONTROLLER_ALL_CURRENCIES = "/controller/all_currencies";
    public static final String PATH_CONTROLLER_RATENB = "/controller/ratenb";
    public static final String PATH_CONTROLLER_RATECB = "/controller/ratecb";
    public static final String PATH_CONTROLLER_DUTIES = "/controller/duties";
    public static final String PATH_CONTROLLER_ENTRIES = "/controller/entries";


    private URLConstant() {
    }
}
