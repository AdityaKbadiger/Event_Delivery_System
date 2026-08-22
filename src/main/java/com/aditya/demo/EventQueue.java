package com.aditya.demo;

import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class EventQueue {
    private final Queue<EventRequest> queue= new ConcurrentLinkedQueue<>();
    public void enqueue(EventRequest event){
        queue.offer(event);//to add event at back

    }
    public EventRequest dequeue(){
        return queue.poll();//to remove event and return the removed event
    }

}
