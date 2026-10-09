package com.aditya.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class EventWorker implements Runnable {
    private final EventQueue eventQueue;
    private final EventService eventService;
    private final DestinationService destinationService;
    private final ExecutorService executor= Executors.newFixedThreadPool(3);
    public EventWorker(EventQueue eventQueue, DestinationService destinationService, EventService eventService){
        this.eventQueue=eventQueue;
        this.destinationService=destinationService;
        this.eventService=eventService;
    }
    @PostConstruct
    public void start(){
        System.out.println("3 worker threads started");
        for (int i=0;i<3;i++){
            executor.submit(this);
        }

    }
    @Override
    public void run(){
        System.out.println("WORKER RUN METHOD STARTED");
        while(!Thread.currentThread().isInterrupted()){
            try{

                EventRequest event= eventQueue.dequeue();
                destinationService.updateStatus(event.getEventId(), EventCreationResult.PROCESSSING);

                System.out.println(Thread.currentThread().getName()+"Received"+event.getEventId());
                try {
                    destinationService.deliver(event);
                    System.out.println(Thread.currentThread().getName()+"Delivered"+event.getEventId());
                    destinationService.updateStatus(event.getEventId(), EventCreationResult.DELIVERED);
                } catch (Exception e) {
                    eventService.incrementRetryCount(event);
                    destinationService.updateStatus(event.getEventId(), EventCreationResult.PENDING);
                    //eventQueue.enqueue(event);
                    //getretrycount which includes sql query and current event s id can be used
                    eventService.nextAttemptAt(event);

                    // i have to enqueue after scheduling
                    throw new RuntimeException(e);
                }


            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }catch (Exception e){
                System.out.println("Delivery error"+Thread.currentThread()+" "+e.getMessage());
            }
        }
    }
    @PreDestroy
    public void stop(){
        System.out.println("Stopping the worker threads ");
        executor.shutdownNow();
        try{
            if (!executor.awaitTermination(3, TimeUnit.SECONDS)){
                System.out.println("the worker does not shutdown in 3 seconds or given time");
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
