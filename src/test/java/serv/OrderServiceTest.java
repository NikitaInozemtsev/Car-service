package serv;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import serv.models.Order;
import serv.repositories.OrderRepository;
import serv.sevices.OrderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Test
    public void getOrders() {

        Order o1 = new Order();
        o1.setId(1);

        Order o2 = new Order();
        o2.setId(2);

        Order o3 = new Order();
        o3.setId(3);


        List<Order> list = new ArrayList<Order>();

        list.add(o1);
        list.add(o2);
        list.add(o3);

        Mockito.when(orderRepository.findAll()).thenReturn(list);

        List<Order> orders = orderService.getOrders();

        Assertions.assertEquals(3,orders.size());
        Assertions.assertEquals(o1, orders.get(0));
        Assertions.assertEquals(o3, orders.get(2));
        Mockito.verify(orderRepository, times(1)).findAll();

    }

}