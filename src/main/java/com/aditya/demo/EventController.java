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
    private final EventQueue eventQueue;
    private final EventService eventService;
    private final DestinationService destinationService;
    public EventController(EventService eventService, DestinationService destinationService, EventQueue eventQueue) {
        this.eventService = eventService;
        this.destinationService=destinationService;
        this.eventQueue=eventQueue;

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
    @PostMapping("deliver-test")
    public String deliverTest(){
        EventRequest event= new EventRequest();
        event.setDestinationUrl("http://localhost:8080/destination");//if we add/does-not-exist or other thing we can find failure point that is the event is not deliverd
        event.setPayload("{\"message\":\"hello from delivery service\"}");
        destinationService.deliver(event);
        return "Delivered";
    }
    @PostMapping("/queue-test")
    public String queueTest(){
        //ventQueue eventQueue= new EventQueue();
        EventRequest event = new EventRequest();
        event.setEventId("queue_test_1");
        event.setEventType("test");
        event.setDestinationUrl("http://localhost:8080/destination");
        event.setPayload("{\\\"message\\\":\\\"hello\\\"}");
        eventQueue.enqueue(event);
        return "Event added to queue";

    }
    @GetMapping("/queue-test")
    public String dequeueTest(){
        try {
            EventRequest event= eventQueue.dequeue();
            if(event==null){
                return "The queue is empty";
            }
            return event.getEventId();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}
