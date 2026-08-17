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
         if(result==EventCreationResult.CREATED){
             ResponseEntity<EventStatus> body;
             status.setId(event.getEventId());
             status.setStatus("CREATED");
             body = ResponseEntity.status(201).body(status);
             return body;
         }
         status.setId(event.getEventId());
         status.setStatus("ALREADY_EXISTS");
         ResponseEntity<EventStatus> body;
         body=ResponseEntity.status(200).body(status);
         return body;

    }
    @GetMapping ("/getevents")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

}
