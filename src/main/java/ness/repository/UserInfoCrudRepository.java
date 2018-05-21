package ness.repository;

import ness.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInfoCrudRepository extends CrudRepository<UserInfo, Integer> {

    UserInfo getById(int id);
    List<UserInfo> findAll();
    boolean existsById(int id);
    void deleteById(int id);
}
