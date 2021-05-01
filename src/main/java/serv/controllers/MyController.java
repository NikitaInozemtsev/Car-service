package serv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import serv.models.*;
import serv.sevices.*;

import java.util.List;

@Controller
public class MyController {


    @Autowired
    private UserService userService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private CarService carService;

    @Autowired
    private WorkService workService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderWorkService orderWorkService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(User user) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String signUp(@ModelAttribute User user) {
        userService.signUpUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/getowners", method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> getOwners() {
        return new ResponseEntity<List<Owner>>(ownerService.getOwners(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getcars", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<List<Car>>(carService.getCars(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createowner", method = RequestMethod.POST)
    public String createOwner(@ModelAttribute Owner owner) {
        ownerService.createOwner(owner);
        return "redirect:/";
    }

    @RequestMapping(value = "/createcar", method = RequestMethod.POST)
    public String createCar(@ModelAttribute Car car) {
        carService.createCar(car);
        return "redirect:/";
    }

    @RequestMapping(value = "/getworks", method = RequestMethod.GET)
    public ResponseEntity<List<KindOfWork>> getWorks() {
        return new ResponseEntity<List<KindOfWork>>(workService.getWorks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    @ResponseBody
    public void createOrder(@RequestParam(value = "car", required = true) int carId,
                            @RequestParam(value = "owner", required = true) int ownerId,
                            @RequestParam(value = "work", required = true) int[] works,
                            @RequestParam(value = "price", required = true) int[] price) {
        Order a = new Order();
        a.setCarId(carId);
        a.setOwnerId(ownerId);
        orderService.createOrder(a);

        for (int i = 0; i < works.length; i++) {
            OrderWork b = new OrderWork();
            b.setOrderId(a.getId());
            b.setWorkId(works[i]);
            b.setPrice(price[i]);
            orderWorkService.createOrderWork(b);
        }
    }

    @RequestMapping(value = "/getorders", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<List<Order>>(orderService.getOrders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("cars", carService.getCars());
        model.addAttribute("owners", ownerService.getOwners());
        model.addAttribute("works", workService.getWorks());
        model.addAttribute("car", new Car());
        model.addAttribute("owner", new Owner());
        return "index";
    }
}