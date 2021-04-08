package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import serv.models.KindOfWork;

public interface KindOfWorkRepository extends JpaRepository<KindOfWork, Integer> {
}
