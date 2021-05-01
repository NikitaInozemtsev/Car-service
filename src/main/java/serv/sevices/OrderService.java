package serv.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.models.Order;
import serv.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository reps;

    public List<Order> getOrders() {
        return reps.findAll();
    }

    public void createOrder(Order a) {
        reps.save(a);
    }
}
