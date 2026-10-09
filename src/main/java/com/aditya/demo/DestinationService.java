package com.aditya.demo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DestinationService {
    private final RestClient restClient;
    private final JdbcTemplate jdbcTemplate;
    public DestinationService(RestClient restClient, JdbcTemplate jdbcTemplate){
        this.restClient=restClient;
        this.jdbcTemplate=jdbcTemplate;
    }
    public void deliver(EventRequest event){
        restClient.post()
                .uri(event.getDestinationUrl())
                .header("Content-type","application/json")
                .body(event.getPayload())
                .retrieve()
                .toBodilessEntity();

    }
    public void updateStatus(String eventId, EventCreationResult status ) {
        String sql = """
                UPDATE events
                SET status =?
                WHERE event_id=?
                """;
        jdbcTemplate.update(sql, status.name(), eventId);
    }
    //i kept this method in this service because when the event enters queue the only this it has to related with is destination

    }