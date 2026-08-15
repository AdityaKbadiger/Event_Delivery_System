package com.aditya.demo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@org.springframework.stereotype.Service
public class EventController {
    private final EventService eventService;



    public EventController(EventService eventService) {
        this.eventService = eventService;

    }

    @PostMapping("/events")
    public String createEvent(@Valid @RequestBody EventRequest event){
        return eventService.createEvent(event);
    }
    @GetMapping ("/getevents")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

}
