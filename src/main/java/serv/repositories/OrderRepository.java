package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
