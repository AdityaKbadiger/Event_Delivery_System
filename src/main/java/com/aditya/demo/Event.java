package com.aditya.demo;

public class Event {
    private Long id;
    private String eventId;
    private String eventType;
    private String destinationUrl;
    private String payload;

    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    public Long getId() {
        return id;
    }
    public void setEventId(String eventId){
        this.eventId=eventId;
    }
    public void setEventType(String eventType){
        this.eventType=eventType;
    }
    public void setPayload(String payload){
        this.payload=payload;
    }
    public String getEventId(){
        return eventId;
    }
    public String getEventType(){
        return eventType;
    }
    public String getDestinationUrl(){
        return destinationUrl;
    }
    public String getPayload(){
        return payload;
    }
    public void setId(Long id){
        this.id=id;
    }


}
