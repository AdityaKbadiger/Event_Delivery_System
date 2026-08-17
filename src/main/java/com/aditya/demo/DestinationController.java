package com.aditya.demo;
import org.springframework.web.bind.annotation.*;
@RestController
public class DestinationController {
    @PostMapping("/destination")
    public String recieveEvent(@RequestBody String payload){
        System.out.println("The destionation recieved"+ payload);
        return "Destination recieved event";
    }

}
