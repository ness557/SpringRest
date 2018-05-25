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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // roles
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public Object getRole(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            return roleService.getRoleById(id);
        } else
            return roleService.getRoles();
    }

    @RequestMapping(value = "/roles", method = RequestMethod.PUT)
    public ResponseEntity putRole(@RequestBody Role role) {

        if(roleService.addRole(role) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.DELETE)
    public ResponseEntity deleteRole(@RequestParam(value = "id") int id) {

        if( roleService.removeRole(id) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public ResponseEntity updateRole(@RequestBody Role role) {

        if(roleService.updateRole(role) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Object getUser(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null)
            return userService.getUserById(id);
        else
            return userService.getUserList();
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity addUser(@RequestBody User user) {

        if(userService.addUser(user) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity updateUser(@RequestBody User user) {

        if(userService.updateUser(user) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestParam(value = "id") int id) {

        if( userService.removeUser(id) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    // students
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Object getStudent(@RequestParam(value = "id", required = false) Integer id) {

        if (id != null)
            return studentService.getStudentById(id);
        else
            return studentService.getStudents();

    }

    @RequestMapping(value = "/students", method = RequestMethod.PUT)
    public ResponseEntity putStudent(@RequestBody Student student) {

        if(studentService.addStudent(student) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/students", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@RequestParam(value = "id") int id){

        if(studentService.removeStudent(id) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);

    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity updateStudent(@RequestBody Student student){

        if(studentService.updateStudent(student) == 1)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
