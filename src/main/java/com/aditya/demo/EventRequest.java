package com.aditya.demo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class EventRequest {
    @NotBlank //is different from not null not null accepts and null values like empty strings and enters to databse
    private String eventId;
    @NotBlank
    private String eventType;
    @NotBlank
    private String destinationUrl;
    @NotBlank
    private String payload;
    public String getEventId(){
        return eventId;
    }
    public void setEventId(String eventId){
        this.eventId=eventId;
    }
    public String getEventType(){
        return eventType;
    }
    public void setEventType(String eventType){
        this.eventType=eventType;
    }
    public String getDestinationUrl(){
        return destinationUrl;

    }
    public void setDestinationUrl(String destinationUrl){
        this.destinationUrl=destinationUrl;
    }
    public String getPayload(){
        return payload;
    }
    public void setPayload(String payload){
        this.payload=payload;
    }




}
