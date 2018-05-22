package ness.repository;

import ness.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserInfoCrudRepository extends CrudRepository<UserInfo, Integer> {

    UserInfo getById(int id);
    List<UserInfo> findAll();
    boolean existsById(int id);
    void deleteById(int id);
}
