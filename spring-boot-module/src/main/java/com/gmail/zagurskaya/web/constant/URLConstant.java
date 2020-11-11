package com.gmail.zagurskaya.web.constant;

public class URLConstant {

    public static final String URL_ADMIN = "/api/admin/**";
    public static final String URL_USERS = "/api/users/**";
    public static final String URL_403 = "/403";
//    public static final String URL_LOGIN = "/login";


    public static final String URL_AUTH = "/api/auth";
    public static final String URL_AUTH_SIGN_UP = "/signup";
    public static final String URL_AUTH_SIGN_IN = "/signin";
    public static final String URL_AUTH_CONFIRM = "/confirm";
    public static final String URL_AUTH_FORGOT_PASSWORD = "/forgot_password";
    public static final String URL_AUTH_CHECK_CODE = "/check_code";
    public static final String URL_AUTH_RESET = "/reset";
    public static final String URL_SIGN_IN = "/api/auth/signin";

    public static final String API_GUEST_TRADERS = "/api/traders";
    public static final String API_GUEST_TRADERS_TOP = "/top";
    public static final String API_GUEST_TRADER_ID = "/{id}";
    public static final String API_GUEST_TRADER_COMMENTS = "/{id}/comments";


    public static final String API_ADMIN = "/api/admin";
    public static final String API_ADMIN_NEW_TRADERS = "/new_traders";
    public static final String API_ADMIN_NEW_TRADER_ID = "/new_traders/{id}";
    public static final String API_ADMIN_NEW_COMMENTS = "/new_comments";
    public static final String API_ADMIN_NEW_COMMENT_ID = "/new_comments/{id}";

    public static final String API_USER = "/api/users/{id}";
    public static final String API_USER_COMMENTS = "/comments";
    public static final String API_USER_COMMENT_ID = "/comments/{idc}";






    private URLConstant() {
    }
}
