package com.aditya.demo;

public class EventStatus {
    private EventCreationResult status;
    String id;

    public void setId(String id){
        this.id=id;
    }
    public void setStatus(EventCreationResult status){
        this.status=status;

    }
    public String getId(){
        return id;
    }
    public EventCreationResult getStatus(){
        return status;
    }

}
