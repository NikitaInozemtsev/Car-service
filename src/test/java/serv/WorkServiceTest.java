package serv;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import serv.models.KindOfWork;
import serv.models.Owner;
import serv.repositories.KindOfWorkRepository;
import serv.repositories.OwnerRepository;
import serv.sevices.OwnerService;
import serv.sevices.WorkService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class WorkServiceTest {
    @InjectMocks
    WorkService workService;

    @Mock
    KindOfWorkRepository kindOfWorkRepository;

    @Test
    public void getWorkById() {
        KindOfWork cl1 = new KindOfWork();
        cl1.setId(1);

        KindOfWork cl2 = new KindOfWork();
        cl2.setId(2);

        Mockito.when(kindOfWorkRepository.findById(1)).thenReturn(java.util.Optional.of(cl1));
        Mockito.when(kindOfWorkRepository.findById(2)).thenReturn(java.util.Optional.of(cl2));

        KindOfWork res1 = workService.getWorkById(1);
        KindOfWork res2 = workService.getWorkById(2);

        Assertions.assertEquals(cl1.getId(), res1.getId());
        Assertions.assertEquals(cl2.getId(), res2.getId());
    }

    @Test
    public void getWorks() {

        KindOfWork cl1 = new KindOfWork();
        cl1.setId(1);
        cl1.setName("name 1");

        KindOfWork cl2 = new KindOfWork();
        cl2.setId(2);
        cl2.setName("name 2");

        Mockito.when(kindOfWorkRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(cl1, cl2)));

        List<KindOfWork> res = workService.getWorks();

        Assertions.assertEquals(Arrays.asList(cl1, cl2), res);
    }
}
