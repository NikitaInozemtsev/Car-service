package serv.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serv.models.Car;
import serv.repositories.CarRepository;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository reps;

    public List<Car> getCars() {
        return reps.findAll();
    }

    public void createCar(Car a) {
        reps.save(a);
    }
}
