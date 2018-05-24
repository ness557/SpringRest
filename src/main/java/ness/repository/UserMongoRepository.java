package ness.repository;

import ness.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserMongoRepository extends MongoRepository<User, Integer> {

    User getById(int id);
    List<User> findAll();
    boolean existsById(int id);
    void deleteById(int id);
}
