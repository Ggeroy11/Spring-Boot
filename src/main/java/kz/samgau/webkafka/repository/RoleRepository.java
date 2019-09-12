package kz.samgau.webkafka.repository;

import kz.samgau.webkafka.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role getByName(String name);
}
