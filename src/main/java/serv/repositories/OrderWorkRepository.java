package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.OrderWork;

public interface OrderWorkRepository extends JpaRepository<OrderWork, Integer> {
}
