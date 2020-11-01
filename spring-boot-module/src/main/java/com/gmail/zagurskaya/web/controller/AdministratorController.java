package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.CommentService;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.Util.UserUtil;
import com.gmail.zagurskaya.service.model.CommentDTO;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_ADMINISTRATOR_ADMIN;
import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_ADMINISTRATOR_NEW_USERS;
import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_ADMINISTRATOR_NEW_USERS_POST;
import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_ADMINISTRATOR_Comment;
import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_ADMINISTRATOR_Comment_REDIRECT;
import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_ADMINISTRATOR_USERS;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMIN;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_EXIT;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_NEW_USERS;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_NEW_USERS_POST;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_Comment;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_Comment_DELETE;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_UPDATE_PASSWORD;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_UPDATE_ROLE;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_USERS_DELETE;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_USERS;

@Controller
@RequestMapping(URL_ADMIN)
public class AdministratorController {
    private static final Logger logger = LogManager.getLogger(AdministratorController.class);
    private final UserService userService;
    private final CommentService CommentService;
    private final UserUtil userUtil;


    public AdministratorController(UserService userService, CommentService CommentService, UserUtil userUtil) {
        this.userService = userService;
        this.CommentService = CommentService;
        this.userUtil = userUtil;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        UserDTO user = userUtil.getActualUser();
        String FullName = user.getFirstName()+" "+user.getLastName();
        model.addAttribute("FullName", FullName);
        return PATH_ADMINISTRATOR_ADMIN;
    }

//    @GetMapping(URL_USERS)
//    public String getUsersInAdminPage(Model model) {
//        List<UserDTO> users = userService.getActionUsersSortedByUserName();
////        List<RoleDTO> roles = roleService.getRoles();
//        model.addAttribute("users", users);
////        model.addAttribute("roles", roles);
//        return PATH_ADMINISTRATOR_USERS;
//    }

    @GetMapping(URL_ADMINISTRATOR_NEW_USERS)
    public String getAddUserInAdminPage() {

        return PATH_ADMINISTRATOR_NEW_USERS;
    }

//    @PostMapping(URL_ADMINISTRATOR_NEW_USERS_POST)
//    public String postAddUserInAdminPage(@ModelAttribute(value = "user") UserDTO userDTO,
//                                         Model model){
//        userDTO.setIsNotActive(false);
//        userService.add(userDTO);
//        return PATH_ADMINISTRATOR_NEW_USERS_POST;
//    }
//
//    @PostMapping(URL_ADMINISTRATOR_USERS_DELETE)
//    public String postDeleteUsersInAdminPage(
//            @RequestParam("ids") List<Long> ids,
//            Model model
//    ) {
//        userService.deleteUsersList(ids);
//        return PATH_ADMINISTRATOR_NEW_USERS_POST;
//    }

//    @PostMapping(URL_ADMINISTRATOR_UPDATE_ROLE)
//    public String postUpdateUserRoleInAdminPage(
//            @RequestParam("id") Long userId,
//            @RequestParam("roleId") Long roleId,
//            Model model) {
//
//        UserDTO userDTO = userService.updateUserRole(userId, roleId);
//        return PATH_ADMINISTRATOR_NEW_USERS_POST;
//    }

//    @PostMapping(URL_ADMINISTRATOR_UPDATE_PASSWORD)
//    public String postUpdateUserPasswordSameAsLoginInAdminPage(
//            @RequestParam("id") Long userId,
//            Model model
//    ) {
//
//        UserDTO userDTO = userService.getUserById(userId);
//        userDTO.setPassword(userService.returnPasswordSameAsLogin(userDTO));
//        userService.updatePassword(userDTO);
//        logger.error("new password = " + userService.returnPasswordSameAsLogin(userDTO));
//        return PATH_ADMINISTRATOR_NEW_USERS_POST;
//    }

    @GetMapping(URL_ADMINISTRATOR_Comment)
    public String getCommentInAdminPage(Model model) {
        List<CommentDTO> Comment = CommentService.getComment();
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("Comment", Comment);
        return PATH_ADMINISTRATOR_Comment;
    }

    @PostMapping(URL_ADMINISTRATOR_Comment_DELETE)
    public String postDeleteCommentInAdminPage(
            @RequestParam("ids") List<Long> ids,
            Model model
    ) {
        CommentService.deleteCommentList(ids);
        return PATH_ADMINISTRATOR_Comment_REDIRECT;
    }

    @GetMapping(URL_ADMINISTRATOR_EXIT)
    public String getExitPage() {

        return "/";
    }
}
