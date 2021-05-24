package serv;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import serv.models.Owner;
import serv.models.User;
import serv.repositories.OwnerRepository;
import serv.repositories.UserRepository;
import serv.sevices.OwnerService;
import serv.sevices.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder encoder;

    @Test
    public void getUsers() {

        User cl1 = new User();
        cl1.setId(1);

        User cl2 = new User();
        cl2.setId(2);

        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(cl1, cl2)));

        List<User> res = userService.getUsers();

        Assertions.assertEquals(Arrays.asList(cl1, cl2), res);
    }
}
