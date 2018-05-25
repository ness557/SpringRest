package ness.controller;

import ness.model.User;
import ness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
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

}
