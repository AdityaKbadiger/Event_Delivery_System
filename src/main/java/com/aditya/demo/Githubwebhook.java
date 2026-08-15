package com.aditya.demo;
import jakarta.validation.constraints.NotNull;
public class Githubwebhook {
    @NotNull
    private String event;
    @NotNull
    private String repository;
    public String getEvent(){
        return event;
    }
    public void setEvent(String event){
        this.event=event;
    }
    public String getRepository(){
        return repository;
    }
    public void setRepository(String repository){
        this.repository=repository;
    }
}
