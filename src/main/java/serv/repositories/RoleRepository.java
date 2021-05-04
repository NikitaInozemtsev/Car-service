package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
