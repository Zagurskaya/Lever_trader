package com.gmail.zagurskaya.web.constant;

public class URLConstant {
    public static final String URL_SIGN_UP = "/api/auth/signup";
    public static final String URL_CONFIRM = "/api/auth/confirm/**";
    public static final String URL_SIGN_IN = "/api/auth/signin";



    public static final String URL_CASH = "/cash";
    public static final String URL_ADMIN = "/admin";
    public static final String URL_CONTROLLER = "/controller";
    public static final String URL_SECURE_REST_API = "SecureRestApi";
    public static final String URL_403 = "/403";
//    public static final String URL_LOGIN = "/login";
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


    public static final String URL_AUTH = "/api/auth";
    public static final String URL_AUTH_SIGN_UP = "/signup";
    public static final String URL_AUTH_CONFIRM = "/confirm";
    public static final String URL_AUTH_FORGOT_PASSWORD = "/forgot_password";

    public static final String API_TRADER = "/api/traders";
    public static final String API_TRADER_ID = "/{id}";
    public static final String API_TRADER_COMMENTS = "/{id}/comments";





    private URLConstant() {
    }
}
