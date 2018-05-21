package ness.service;

import ness.model.Role;
import java.util.List;

public interface RoleService {

    int addRole(Role role);
    int updateRole(Role role);
    int removeRole(Role role);
    int removeRole(int id);
    Role getRoleById(int id);
    List<Role> getRoles();

}
