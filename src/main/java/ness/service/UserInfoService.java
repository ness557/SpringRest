package ness.service;

import ness.model.UserInfo;

import java.util.List;

public interface UserInfoService {

    int addUserInfo(UserInfo userInfo);
    int updateUserInfo(UserInfo userInfo);
    int removeUserInfo(UserInfo userInfo);
    int removeUserInfo(int id);
    UserInfo getUserInfoById(int id);
    List<UserInfo> getUserInfoList();
}
