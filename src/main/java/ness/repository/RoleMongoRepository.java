package ness.repository;

import ness.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleMongoRepository extends MongoRepository<Role, Integer> {

    Role getById(Integer id);
    List<Role> findAll();
    Role findByName(String name);
    boolean existsById(int id);
    void deleteById(int id);

}
