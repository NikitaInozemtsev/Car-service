package serv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import serv.models.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    @Modifying
    @Query("update User u set u.password = ?1 where u.id = ?2")
    void changePassword(String password, Integer id);
}