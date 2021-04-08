package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
}
