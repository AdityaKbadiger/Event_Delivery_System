package com.aditya.demo;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EventStatus> createEvent(@Valid @RequestBody EventRequest event){
        EventCreationResult result= eventService.createEvent(event);
        EventStatus status= new EventStatus();
        status.setId(event.getEventId());
        status.setStatus(result);
         if(result==EventCreationResult.CREATED){

             return ResponseEntity.status(201).body(status);

         }

         return ResponseEntity.status(200).body(status);


    }
    @GetMapping ("/getevents")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

}
