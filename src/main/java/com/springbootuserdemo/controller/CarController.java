package com.springbootuserdemo.controller;

import com.springbootuserdemo.model.Car;
import com.springbootuserdemo.model.UserProfile;
import com.springbootuserdemo.service.CarService;
import com.springbootuserdemo.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class CarController {

    public static final Logger log = LoggerFactory.getLogger(CarController.class);

    private CarService carService;
    private MyUserDetailsService myUserDetailsService;


    //TODO add modelAttribute to each view userProfile

    public CarController(CarService carService, MyUserDetailsService myUserDetailsService) {
        this.carService = carService;
        this.myUserDetailsService = myUserDetailsService;
    }

    @RequestMapping(value={"/admin/newCar"}, method = RequestMethod.GET)
    public ModelAndView newUser(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/newCar");

        //Form object
        Car car = new Car();
        modelAndView.addObject("car", car);

        return modelAndView;
    }

    @RequestMapping(value = "/admin/newCar", method = RequestMethod.POST)
    public ModelAndView saveNewCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult, ModelAndView modelAndView, Principal principal) {

        if(bindingResult.hasErrors()){
            log.info("bindingResult has errors");
            modelAndView.addObject("car", car);
            modelAndView.setViewName("admin/newCar");
        }
        else
        {

            log.info("saveNewUser and set values");
            //String loggedInUser = principal.getName();

            carService.saveCar(car);
            String successMessage = "Car " + car.getMake() + " " + car.getModel() + " Succussfully Added";

            Car newCar = new Car();
            modelAndView.addObject("car", newCar);
            modelAndView.addObject("successMessage", successMessage);
            modelAndView.setViewName("admin/newCar");
        }

        return modelAndView;
    }

    @RequestMapping(value={"/admin/allCars"}, method = RequestMethod.GET)
    public ModelAndView allCars(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/allCars");

        List<Car> allCars = carService.findAllCars();
        modelAndView.addObject("allCars", allCars);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserProfile user = myUserDetailsService.findUserByUsername(auth.getName());
        Long userId = user.getId();
        modelAndView.addObject("userId", userId);

        return modelAndView;
    }



    @RequestMapping(value = "/admin/updateCar/{id}", method = RequestMethod.GET)
    public ModelAndView updateCar(@PathVariable Long id) {


        Car car = carService.findCarById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("car", car);
        modelAndView.setViewName("admin/newCar");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/deleteCar/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> disableUser(@PathVariable Long id) {

        log.info("should delete car with id: " + id);
        carService.deleteCar(id);
        return ResponseEntity.ok("Car successfully deleted");
//      return ResponseEntity.badRequest();
    }

}
