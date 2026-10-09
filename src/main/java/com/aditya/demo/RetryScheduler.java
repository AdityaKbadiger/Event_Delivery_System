package com.aditya.demo;

import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RetryScheduler {
    private final EventQueue eventQueue;
    private final JdbcTemplate jdbcTemplate;
    public RetryScheduler(EventQueue eventQueue, JdbcTemplate jdbcTemplate){
        this.eventQueue=eventQueue;
        this.jdbcTemplate=jdbcTemplate;
    }
    @Scheduled(fixedDelay = 1000)
    public void CheckForRetries(){
        System.out.println("RETRY SCHEDULER RUNNING");




    }
}