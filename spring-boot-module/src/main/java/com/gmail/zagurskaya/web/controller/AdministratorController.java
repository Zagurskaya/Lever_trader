package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.ReviewsService;
import com.gmail.zagurskaya.service.RoleService;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.Util.UserUtil;
import com.gmail.zagurskaya.service.model.ReviewsDTO;
import com.gmail.zagurskaya.service.model.RoleDTO;
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
import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_ADMINISTRATOR_REVIEWS;
import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_ADMINISTRATOR_REVIEWS_REDIRECT;
import static com.gmail.zagurskaya.web.constant.URLConstant.PATH_ADMINISTRATOR_USERS;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMIN;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_EXIT;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_NEW_USERS;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_NEW_USERS_POST;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_REVIEWS;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_REVIEWS_DELETE;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_UPDATE_PASSWORD;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_UPDATE_ROLE;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_ADMINISTRATOR_USERS_DELETE;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_USERS;

@Controller
@RequestMapping(URL_ADMIN)
public class AdministratorController {
    private static final Logger logger = LogManager.getLogger(AdministratorController.class);
    private final UserService userService;
    private final RoleService roleService;
    private final ReviewsService reviewsService;
    private final UserUtil userUtil;


    public AdministratorController(UserService userService, RoleService roleService, ReviewsService reviewsService, UserUtil userUtil) {
        this.userService = userService;
        this.roleService = roleService;
        this.reviewsService = reviewsService;
        this.userUtil = userUtil;
    }

    @GetMapping
    public String getAdminPage(Model model) {
        UserDTO user = userUtil.getActualUser();
        String FullName = user.getFirstName()+" "+user.getLastName()+" "+user.getPatronymic();
        model.addAttribute("FullName", FullName);
        return PATH_ADMINISTRATOR_ADMIN;
    }

    @GetMapping(URL_USERS)
    public String getUsersInAdminPage(Model model) {
        List<UserDTO> users = userService.getActionUsersSortedByUserName();
        List<RoleDTO> roles = roleService.getRoles();
        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        return PATH_ADMINISTRATOR_USERS;
    }

    @GetMapping(URL_ADMINISTRATOR_NEW_USERS)
    public String getAddUserInAdminPage() {

        return PATH_ADMINISTRATOR_NEW_USERS;
    }

    @PostMapping(URL_ADMINISTRATOR_NEW_USERS_POST)
    public String postAddUserInAdminPage(@ModelAttribute(value = "user") UserDTO userDTO,
                                         Model model){
        userDTO.setIsNotActive(false);
        userService.add(userDTO);
        return PATH_ADMINISTRATOR_NEW_USERS_POST;
    }

    @PostMapping(URL_ADMINISTRATOR_USERS_DELETE)
    public String postDeleteUsersInAdminPage(
            @RequestParam("ids") List<Long> ids,
            Model model
    ) {
        userService.deleteUsersList(ids);
        return PATH_ADMINISTRATOR_NEW_USERS_POST;
    }

    @PostMapping(URL_ADMINISTRATOR_UPDATE_ROLE)
    public String postUpdateUserRoleInAdminPage(
            @RequestParam("id") Long userId,
            @RequestParam("roleId") Long roleId,
            Model model) {

        UserDTO userDTO = userService.updateUserRole(userId, roleId);
        return PATH_ADMINISTRATOR_NEW_USERS_POST;
    }

    @PostMapping(URL_ADMINISTRATOR_UPDATE_PASSWORD)
    public String postUpdateUserPasswordSameAsLoginInAdminPage(
            @RequestParam("id") Long userId,
            Model model
    ) {

        UserDTO userDTO = userService.getUserById(userId);
        userDTO.setPassword(userService.returnPasswordSameAsLogin(userDTO));
        userService.updatePassword(userDTO);
        logger.error("new password = " + userService.returnPasswordSameAsLogin(userDTO));
        return PATH_ADMINISTRATOR_NEW_USERS_POST;
    }

    @GetMapping(URL_ADMINISTRATOR_REVIEWS)
    public String getReviewsInAdminPage(Model model) {
        List<ReviewsDTO> reviews = reviewsService.getReviews();
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users", users);
        model.addAttribute("reviews", reviews);
        return PATH_ADMINISTRATOR_REVIEWS;
    }

    @PostMapping(URL_ADMINISTRATOR_REVIEWS_DELETE)
    public String postDeleteReviewsInAdminPage(
            @RequestParam("ids") List<Long> ids,
            Model model
    ) {
        reviewsService.deleteReviewsList(ids);
        return PATH_ADMINISTRATOR_REVIEWS_REDIRECT;
    }

    @GetMapping(URL_ADMINISTRATOR_EXIT)
    public String getExitPage() {

        return "/";
    }
}
