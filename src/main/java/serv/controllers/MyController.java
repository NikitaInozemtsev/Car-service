package serv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import serv.models.*;
import serv.sevices.*;

import java.security.Principal;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model, Principal principal) {
        model.addAttribute("cars", carService.getCars());
        model.addAttribute("owners", ownerService.getOwners());
        model.addAttribute("works", workService.getWorks());
        model.addAttribute("car", new Car());
        model.addAttribute("owner", new Owner());
        model.addAttribute("user", principal.getName());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.GET)
    public String registration(User user) {
        return "registration";
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.POST)
    String signUp(@ModelAttribute User user) {
        userService.signUpUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/isusername", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isUsernameExist(@RequestParam String username) {
        userService.loadUserByUsername(username);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/creatework", method = RequestMethod.GET)
    public String getWindowCreateWork(KindOfWork kindOfWork) {
        return "work";
    }

    @RequestMapping(value = "/admin/creatework", method = RequestMethod.POST)
    public String createKindOfWork(@ModelAttribute KindOfWork kindOfWork) {
        workService.createKindOfWork(kindOfWork);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/deleteuser", method = RequestMethod.GET)
    public String windowDeleteUser(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "deleteuser";
    }

    @RequestMapping(value = "/admin/deleteuser", method = RequestMethod.POST)
    public Object deleteUser(@RequestParam(value = "user", required = true) int id) {
        if (id != 1) {
            userService.deleteUser(id);
            return "redirect:/admin";
        }
        else {
            return new ResponseEntity<String>("Нельзя удалить admin-а", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/admin/deletework", method = RequestMethod.GET)
    public String windowDeleteWork(Model model) {
        model.addAttribute("works", workService.getWorks());
        return "deletework";
    }

    @RequestMapping(value = "/admin/deletework", method = RequestMethod.POST)
    public Object deleteWork(@RequestParam(value = "work", required = true) int id) {
        workService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/getowners", method = RequestMethod.GET)
    public ResponseEntity<List<Owner>> getOwners() {
        return new ResponseEntity<List<Owner>>(ownerService.getOwners(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getcars", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<List<Car>>(carService.getCars(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getworks", method = RequestMethod.GET)
    public ResponseEntity<List<KindOfWork>> getWorks() {
        return new ResponseEntity<List<KindOfWork>>(workService.getWorks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getorders", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<List<Order>>(orderService.getOrders(), HttpStatus.OK);
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

    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public String windowChangePassword() {
        return "password";
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam(value = "oldPassword", required = true) String oldPassword,
                                 @RequestParam(value = "newPassword", required = true) String newPassword,
                                 Principal principal,
                                 Model model) throws Exception {
        try {
            userService.changePassword(principal.getName(), oldPassword, newPassword);
            return "redirect:/";
        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());

            return "password";

        }

    }

}