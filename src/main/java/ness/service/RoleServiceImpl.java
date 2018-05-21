package ness.service;

import ness.model.Role;
import ness.repository.RoleCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleCrudRepository repository;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public RoleServiceImpl(RoleCrudRepository roleCrudRepository){
        this.repository = roleCrudRepository;
    }

    @Override
    public int addRole(Role role) {
        logger.info("Trying to add role " + role);

        boolean isExists = repository.existsById(role.getId());

        if (!isExists && repository.save(role) != null) {
            logger.info("Role added");
            return 1;
        }
        logger.info("Role NOT added");
        return 0;
    }

    @Override
    public int updateRole(Role role) {
        logger.info("Trying to update role " + role);

        Role rolee = repository.getById(role.getId());
        if(rolee != null){
            rolee.setName(role.getName());
            repository.save(rolee);
            logger.info("Role updated");
            return 1;
        }
        logger.info("Role NOT updated");
        return 0;
    }

    @Override
    public int removeRole(Role role) {
        logger.info("Trying to delete role " + role);

        if (repository.existsById(role.getId())) {
            repository.delete(role);
            if (!repository.existsById(role.getId())) {
                logger.info("Role deleted");
                return 1;
            }
        }
        logger.info("Role NOT deleted");
        return 0;
    }

    @Override
    public int removeRole(int id) {
        logger.info("Trying to delete role by id = " + id);

        if (repository.existsById(id)) {
            repository.deleteById(id);
            if (!repository.existsById(id)) {
                logger.info("Role deleted");
                return 1;
            }
        }
        logger.info("Role NOT deleted");
        return 0;
    }

    @Override
    public Role getRoleById(int id) {
        logger.info("Trying to find role by id = " + id);
        Role role = repository.getById(id);

        if (role != null)
            logger.info("Role found: " + role);
        else
            logger.info("Role NOT found");

        return role;
    }

    @Override
    public List<Role> getRoles() {
        logger.info("Trying to get roles list");

        List<Role> roles = repository.findAll();

        if (roles.isEmpty())
            logger.info("List is empty");
        else
            logger.info("Got list");

        return roles;
    }

    @Override
    public Role findRoleByName(String name) {
        logger.info("Trying to find role by name = " + name);
        Role role = repository.findByName(name);

        if (role != null)
            logger.info("Role found: " + role);
        else
            logger.info("Role NOT found");

        return role;
    }
}
