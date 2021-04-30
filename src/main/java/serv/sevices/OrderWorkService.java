package serv.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.models.OrderWork;
import serv.repositories.OrderWorkRepository;

@Service
public class OrderWorkService {
    @Autowired
    private OrderWorkRepository reps;

    public void createOrderWork(OrderWork a) {
        reps.save(a);
    }
}
