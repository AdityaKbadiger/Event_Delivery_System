package com.aditya.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.SynthOptionPaneUI;
@Component
public class EventWorker implements Runnable {
    private final EventQueue eventQueue;
    private final DestinationService destinationService;
    public EventWorker(EventQueue eventQueue, DestinationService destinationService){
        this.eventQueue=eventQueue;
        this.destinationService=destinationService;
    }
    @PostConstruct
    public void start(){
        System.out.println("START METHOD CALLED ");
        Thread workThread= new Thread(this);
        workThread.start();
        System.out.println("STARTING WORKER THREAD");
    }
    @Override
    public void run(){
        System.out.println("WORKER RUN METHOD STARTED");
        while(true){
            try{
                System.out.println("WORKER WAITING FOR EVENT ");
                EventRequest event= eventQueue.dequeue();
                System.out.println(Thread.currentThread().getName()+"Recieved"+event.getEventId());
                destinationService.deliver(event);
                System.out.println("WORKER DELIVERD");
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
