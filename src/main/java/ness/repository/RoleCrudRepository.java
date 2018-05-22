package ness.repository;

import ness.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface RoleCrudRepository extends CrudRepository<Role, Integer> {

    Role getById(Integer id);
    List<Role> findAll();
    Role findByName(String name);
    boolean existsById(int id);
    void deleteById(int id);

}
