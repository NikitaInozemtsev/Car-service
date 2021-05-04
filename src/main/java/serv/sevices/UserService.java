package serv.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import serv.models.Role;
import serv.models.User;
import serv.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository reps;

    @Autowired
    UserService(UserRepository reps, BCryptPasswordEncoder encoder) {
        this.reps = reps;
        this.encoder = encoder;
        User admin = new User();
        admin.setId(1);
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin"));
        Set<Role> set = new HashSet<Role>();
        set.add(new Role(1, "ROLE_USER"));
        set.add(new Role(2, "ROLE_ADMIN"));
        admin.setRoles(set);
        User user = reps.findByUsername(admin.getUsername());
        if (user == null) {
            reps.save(admin);
        }

    }

    public void signUpUser(User user) {

        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setPassword(encoder.encode(user.getPassword()));
        reps.save(user);

    }

    public void deleteUser(int userId) {
        if (reps.findById(userId).isPresent()) {
            reps.deleteById(userId);
        }
    }

    public List<User> getUsers() {
        return reps.findAll();
    }

    @Transactional
    public void changePassword(String username, String oldPassword, String newPassword) throws Exception {
        User a = reps.findByUsername(username);
        if (encoder.matches(oldPassword, a.getPassword())){
            if (encoder.matches(newPassword, a.getPassword())){
                throw new Exception("Пароль не должен совпадать с предыдущим");
            }
            a.setPassword(encoder.encode(newPassword));
            reps.changePassword(a.getPassword(), a.getId());
            return;
        }
        throw new Exception("Неверный пароль");

    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = reps.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}