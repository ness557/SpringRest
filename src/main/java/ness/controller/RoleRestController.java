package ness.controller;

import ness.model.Role;
import ness.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RoleRestController {

    private RoleService service;

    @Autowired
    public RoleRestController(RoleService roleService){
        service = roleService;
    }

    @RequestMapping(value = "roles", method = RequestMethod.GET)
    public List<Role> getRoles(@RequestParam(value = "id", required = false) Integer id){

        if(id != null)
            return new LinkedList<Role>(){{
                add(service.getRoleById(id));
            }};
        else
            return service.getRoles();
    }

    @RequestMapping(value = "roles", method = RequestMethod.PUT)
    public int putRole(@RequestParam(value = "name") String name){

        Role role = new Role();
        role.setName(name);
        return service.addRole(role);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.DELETE)
    public int deleteRole(@RequestParam(value = "id") int id){

        return service.removeRole(id);
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public int updateRole(@RequestParam(value = "id") int id,
                          @RequestParam(value = "name") String name){

        Role role = new Role();
        role.setId(id);
        role.setName(name);
        return service.updateRole(role);
    }


}
