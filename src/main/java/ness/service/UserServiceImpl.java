package ness.service;

import ness.model.User;
import ness.model.UserInfo;
import ness.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private UserCrudRepository repository;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public UserServiceImpl(UserCrudRepository userCrudRepository) {
        this.repository = userCrudRepository;
    }

    @Override
    public int addUser(User user) {
        logger.info("Trying to add user " + user);

        boolean isExists = repository.existsById(user.getId());

        if (!isExists && repository.save(user) != null) {
            logger.info("user added");
            return 1;
        }
        logger.info("user NOT added");
        return 0;
    }

    @Override
    public int updateUser(User user) {
        logger.info("Trying to update user_info " + user);

        User userr = repository.getById(user.getId());
        if (userr != null) {
            userr.setUsername(user.getUsername());
            userr.setPassword(user.getPassword());
            userr.setRoles(user.getRoles());
            user.setUserInfo(user.getUserInfo());
            repository.save(user);
            logger.info("user updated");
            return 1;
        }
        logger.info("user NOT updated");
        return 0;
    }

    @Override
    public int removeUser(User user) {
        logger.info("Trying to delete user " + user);

        if (repository.existsById(user.getId())) {

            repository.delete(user);
            if (!repository.existsById(user.getId())) {
                logger.info("user deleted");
                return 1;
            }
        }
        logger.info("user NOT deleted");
        return 0;
    }

    @Override
    public int saveOrUpdate(User user) {
        logger.info("Trying to save or update user " + user);

        user = repository.save(user);
        if (user != null) {
            logger.info("user updated");
            return 1;
        } else {
            logger.info("user NOT updated");
            return 0;
        }
    }

    @Override
    public int removeUser(int id) {
        logger.info("Trying to delete user by id = " + id);

        if (repository.existsById(id)) {
            repository.deleteById(id);
            if (!repository.existsById(id)) {
                logger.info("user deleted");
                return 1;
            }
        }
        logger.info("user NOT deleted");
        return 0;
    }

    @Override
    public User getUserById(int id) {
        logger.info("Trying to find user by id = " + id);
        User user = repository.getById(id);

        if (user != null)
            logger.info("User_info found: " + user);
        else
            logger.info("User_info NOT found");

        return user;
    }

    @Override
    public List<User> getUserList() {
        logger.info("Trying to get users list");

        List<User> users = repository.findAll();

        if (users.isEmpty())
            logger.info("List is empty");
        else
            logger.info("Got list");

        return users;
    }
}
