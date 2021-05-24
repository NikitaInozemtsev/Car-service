package serv;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import serv.models.Owner;
import serv.repositories.OrderRepository;
import serv.repositories.OwnerRepository;
import serv.sevices.OrderService;
import serv.sevices.OwnerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OwnerServiceTest {
    @InjectMocks
    OwnerService ownerService;

    @Mock
    OwnerRepository ownerRepository;

    @Test
    public void getOwnerById() {
        Owner cl1 = new Owner();
        cl1.setId(1);

        Owner cl2 = new Owner();
        cl2.setId(2);

        Mockito.when(ownerRepository.findById(1)).thenReturn(java.util.Optional.of(cl1));
        Mockito.when(ownerRepository.findById(2)).thenReturn(java.util.Optional.of(cl2));

        Owner res1 = ownerService.getOwnerById(1);
        Owner res2 = ownerService.getOwnerById(2);

        Assertions.assertEquals(cl1.getId(), res1.getId());
        Assertions.assertEquals(cl2.getId(), res2.getId());
    }

    @Test
    public void getOwners() {

        Owner cl1 = new Owner();
        cl1.setId(1);
        cl1.setPassport("2345");

        Owner cl2 = new Owner();
        cl2.setId(2);
        cl2.setPassport("1092");

        Mockito.when(ownerRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(cl1, cl2)));

        List<Owner> res = ownerService.getOwners();

        Assertions.assertEquals(Arrays.asList(cl1, cl2), res);
    }
}
