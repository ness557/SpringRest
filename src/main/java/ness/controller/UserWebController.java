package ness.controller;

import org.apache.commons.lang3.StringUtils;
import ness.model.Role;
import ness.model.User;
import ness.model.UserInfo;
import ness.service.RoleService;
import ness.service.UserInfoService;
import ness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/web/users")
public class UserWebController {

    private UserService userService;
    private UserInfoService infoService;
    private RoleService roleService;

    @Autowired
    public UserWebController(UserService userService, UserInfoService userInfoService, RoleService roleService) {
        this.userService = userService;
        this.infoService = userInfoService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getUsers(Model model) {

        model.addAttribute("users list", userService.getUserList());
        return "show_users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {

        User user = new User();
        UserInfo info = new UserInfo();

        model.addAttribute("user", user)
                .addAttribute("info", info)
                .addAttribute("roleList", roleService.getRoles());
        return "form_user";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateUser(@RequestParam(value = "id") int id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user)
                .addAttribute("info", user.getUserInfo())
                .addAttribute("roleList", roleService.getRoles());

        return "form_user";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String deleteUser(@RequestParam(value = "id") int id) {

        userService.removeUser(id);
        return "redirect:/list";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user,
                           @ModelAttribute("info") UserInfo info,
                           @ModelAttribute("roles") String roles) {
        Set<Role> roleSet = new HashSet<>();

        for (String roleName: roles.split(",")) {
            Role role = roleService.findRoleByName(
                    StringUtils.deleteWhitespace(roleName));
            roleSet.add(role);
        }

        user.setUserInfo(info);
        user.setRoles(roleSet);

        return "redirect:/list";
    }

}
