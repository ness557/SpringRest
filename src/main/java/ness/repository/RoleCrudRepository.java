package ness.repository;

import ness.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleCrudRepository extends CrudRepository<Role, Integer> {
}
