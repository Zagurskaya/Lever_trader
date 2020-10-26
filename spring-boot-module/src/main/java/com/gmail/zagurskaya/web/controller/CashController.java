package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.Util.UserUtil;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_CASH_HOME;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_CASH;


@Controller
@RequestMapping(URL_CASH)
public class CashController {

    private final UserService userService;
    private final UserUtil userUtil;

    @Autowired
    public CashController(UserService userService, UserUtil userUtil) {
        this.userService = userService;
        this.userUtil = userUtil;
    }

    @GetMapping
    public String getUsersPage(Model model) {
        UserDTO user = userUtil.getActualUser();
        String FullName = user.getFirstName()+" "+user.getLastName()+" "+user.getPatronymic();
        model.addAttribute("FullName", FullName);
        return PATH_CASH_HOME;
    }
}
