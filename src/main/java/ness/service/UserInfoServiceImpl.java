package ness.service;

import ness.model.Role;
import ness.model.UserInfo;
import ness.repository.UserInfoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoCrudRepository repository;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public UserInfoServiceImpl(UserInfoCrudRepository userInfoCrudRepository) {
        this.repository = userInfoCrudRepository;
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        logger.info("Trying to add user_info " + userInfo);

        boolean isExists = repository.existsById(userInfo.getId());

        if (!isExists && repository.save(userInfo) != null) {
            logger.info("User_info added");
            return 1;
        }
        logger.info("User_info NOT added");
        return 0;
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        logger.info("Trying to update user_info " + userInfo);

        UserInfo userInfoo = repository.getById(userInfo.getId());
        if (userInfoo != null) {
            userInfoo.setEmail(userInfo.getEmail());
            userInfo.setPhone(userInfo.getPhone());
            repository.save(userInfo);
            logger.info("User_info updated");
            return 1;
        }
        logger.info("User_info NOT updated");
        return 0;
    }

    @Override
    public int removeUserInfo(UserInfo userInfo) {
        logger.info("Trying to delete user_info " + userInfo);

        if (repository.existsById(
                userInfo.getId())) {
            repository.delete(userInfo);
            if (!repository.existsById(userInfo.getId())) {
                logger.info("User_info deleted");
                return 1;
            }
        }
        logger.info("User_info NOT deleted");
        return 0;
    }

    @Override
    public int removeUserInfo(int id) {
        logger.info("Trying to delete user_info by id = " + id);

        if (repository.existsById(id)) {
            repository.deleteById(id);
            if (!repository.existsById(id)) {
                logger.info("User_info deleted");
                return 1;
            }
        }
        logger.info("User_info NOT deleted");
        return 0;
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        logger.info("Trying to find user_info by id = " + id);
        UserInfo info = repository.getById(id);

        if (info != null)
            logger.info("User_info found: " + info);
        else
            logger.info("User_info NOT found");

        return info;
    }

    @Override
    public List<UserInfo> getUserInfoList() {
        logger.info("Trying to get user_info list");

        List<UserInfo> infos = repository.findAll();

        if (infos.isEmpty())
            logger.info("List is empty");
        else
            logger.info("Got list");

        return infos;
    }
}
