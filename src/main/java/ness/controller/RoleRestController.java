package ness.controller;

import ness.model.Role;
import ness.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleRestController {

    private RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
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
}
