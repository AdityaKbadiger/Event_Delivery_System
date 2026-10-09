package com.aditya.demo;

import org.springframework.stereotype.Component;

@Component
public class SchedulerLogic {
    public long time(int retry_count) {
        long time = (long)Math.pow(2, (retry_count - 1));
        return time;
    }

}
