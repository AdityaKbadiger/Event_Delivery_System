package com.aditya.demo;

import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class EventQueue {
    private final BlockingQueue<EventRequest> queue= new LinkedBlockingQueue<>();
    public void enqueue(EventRequest event){
        queue.offer(event);//to add event at back

    }
    public EventRequest dequeue() throws InterruptedException {
        return queue.take();//to remove event and return the removed event
    }

}
