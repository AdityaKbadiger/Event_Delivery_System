package com.aditya.demo;
import org.springframework.web.bind.annotation.*;
@RestController
public class DestinationController {
    @PostMapping("/destination")
    public String recieveEvent(@RequestBody String payload) throws InterruptedException {
        System.out.println("DESTINATION RECIEVED");
        Thread.sleep(20000);
        System.out.println("The destionation recieved"+ payload);
        return "Destination recieved event";
    }

}
