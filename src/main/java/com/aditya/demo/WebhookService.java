package com.aditya.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebhookService {
    private JdbcTemplate jdbcTemplate;
    public WebhookService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;;
    }

    public String processWebhook(Githubwebhook data){
        String sql= "INSERT INTO webhook(event, repository) VALUES (?,?)";
        jdbcTemplate.update(sql,data.getEvent(),data.getRepository());
        return "Saved to DB using SQL";

    }

}
