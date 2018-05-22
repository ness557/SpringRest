package ness.repository;

import ness.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UserInfoCrudRepository extends CrudRepository<UserInfo, Long> {

    UserInfo getById(int id);
    List<UserInfo> findAll();
    boolean existsById(int id);
    void deleteById(int id);
}
