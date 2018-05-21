package ness.repository;

import ness.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RoleCrudRepository extends CrudRepository<Role, Integer> {

    Role getOne(Integer id);
    List<Role> findAll();
    boolean existsById(int id);
    boolean existsByIdAndName(int id, String name);
    void deleteById(int id);

}
