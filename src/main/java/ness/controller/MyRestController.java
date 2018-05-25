package ness.controller;

import ness.model.Role;
import ness.model.Student;
import ness.model.User;
import ness.model.UserInfo;
import ness.service.RoleService;
import ness.service.StudentService;
import ness.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class MyRestController {

    private RoleService roleService;
    private StudentService studentService;

    private UserService userService;

    @Autowired
    public MyRestController(RoleService roleService, UserService userService, StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public Object getRole(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            return roleService.getRoleById(id);
        } else
            return roleService.getRoles();
    }

    @RequestMapping(value = "/roles", method = RequestMethod.PUT)
    public int putRole(@RequestParam(value = "name") String name) {

        Role role = new Role();
        role.setName(name);
        return roleService.addRole(role);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.DELETE)
    public int deleteRole(@RequestParam(value = "id") int id) {

        return roleService.removeRole(id);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public int updateRole(@RequestParam(value = "id") int id,
                          @RequestParam(value = "name") String name) {

        Role role = new Role();
        role.setId(id);
        role.setName(name);
        return roleService.updateRole(role);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Object getUser(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null)
            return userService.getUserById(id);
        else
            return userService.getUserList();
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public int addUser(@RequestParam(value = "username") String username,
                       @RequestParam(value = "password") String password,
                       @RequestParam(value = "email") String email,
                       @RequestParam(value = "phone") int phone,
                       @RequestParam(value = "roles") String roles) {

        UserInfo info = new UserInfo();
        info.setEmail(email);
        info.setPhone(phone);

        User user = new User();
        user.setUserInfo(info);
        user.setUsername(username);
        user.setPassword(password);

        Set<Role> roleSet = new HashSet<>();

        for (String roleName : roles.split(",")) {
            Role role = roleService.findRoleByName(
                    StringUtils.deleteWhitespace(roleName));
            roleSet.add(role);
        }

        user.setRoles(roleSet);

        return userService.saveOrUpdate(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public int updateUser(@RequestParam(value = "id") Integer id,
                          @RequestParam(value = "username") String username,
                          @RequestParam(value = "password") String password,
                          @RequestParam(value = "email") String email,
                          @RequestParam(value = "phone") int phone,
                          @RequestParam(value = "roles") String roles) {

        User user = userService.getUserById(id);
        if (user == null)
            return 0;

        user.getUserInfo().setEmail(email);
        user.getUserInfo().setPhone(phone);
        user.setUsername(username);
        user.setPassword(password);

        Set<Role> roleSet = new HashSet<>();

        for (String roleName : roles.split(",")) {
            Role role = roleService.findRoleByName(
                    StringUtils.deleteWhitespace(roleName));
            roleSet.add(role);
        }

        user.setRoles(roleSet);

        return userService.saveOrUpdate(user);
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<Student> getStudent(@RequestParam(value = "id", required = false) Integer id) {

        if (id != null)
            return new LinkedList<Student>() {{
                add(studentService.getStudentById(id));
            }};
        else
            return studentService.getStudents();

    }

    @RequestMapping(value = "/students", method = RequestMethod.PUT)
    public int putStudent(@RequestParam(value = "id") int id,
                          @RequestParam(value = "name") String name,
                          @RequestParam(value = "group") String group) {

        return studentService.addStudent(new Student(id, name, group));
    }

    @RequestMapping(value = "/students", method = RequestMethod.DELETE)
    public int deleteStudent(@RequestParam(value = "id") int id){

        return studentService.removeStudent(id);

    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public int updateStudent(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "group") String group){

        return studentService.updateStudent(new Student(id, name, group));
    }
}
