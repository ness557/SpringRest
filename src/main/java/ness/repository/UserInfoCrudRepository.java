package ness.repository;

import ness.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoCrudRepository extends CrudRepository<UserInfo, Integer> {
}
