package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
