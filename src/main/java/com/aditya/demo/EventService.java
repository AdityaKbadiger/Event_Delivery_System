package com.aditya.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EventService {
    private final EventQueue eventQueue;
    private final JdbcTemplate jdbcTemplate;
    private final SchedulerLogic schedulerLogic;
    public EventService(JdbcTemplate jdbcTemplate, EventQueue eventQueue, SchedulerLogic schedulerLogic) {
        this.jdbcTemplate = jdbcTemplate;
        this.eventQueue=eventQueue;
        this.schedulerLogic=schedulerLogic;
    }
    public EventCreationResult createEvent(EventRequest event){
        try {
            String sql ="""
                INSERT INTO events
                (event_id, event_type, destination_url, payload,status)
                VALUES (?, ?, ?, ?,?)
                """;
            jdbcTemplate.update(sql,event.getEventId(),event.getEventType(),event.getDestinationUrl(),event.getPayload(),EventCreationResult.PENDING.name()
            );
            eventQueue.enqueue(event);

            return EventCreationResult.CREATED;


        }
        catch (DuplicateKeyException e){
            return EventCreationResult.ALREADY_EXISTS;
        }

    }

    public List<Event> getAllEvents(){
        String sql= """
                SELECT id, event_Id, event_Type, destination_Url, payload
                FROM events
                """;


        List<Event> events = jdbcTemplate.query(sql, new RowMapper<Event>() {
            @Override
            public Event mapRow(java.sql.ResultSet rs, int rowNum)
                    throws java.sql.SQLException {
                Event event= new Event();
                event.setEventId(rs.getString("id"));
                event.setEventId(rs.getString("event_id"));
                event.setEventType(rs.getString("event_type"));
                event.setDestinationUrl(rs.getString("destination_url"));
                event.setPayload(rs.getString("payload"));
                return event;
            }

        });
        return events;
    }
    public void incrementRetryCount(EventRequest event){
        String sqlretry = """
                UPDATE events
                SET retry_count=retry_count+1
                WHERE event_id =?
                """;
        jdbcTemplate.update(sqlretry,event.getEventId());

    }
    public void nextAttemptAt(EventRequest event){
        int newtime= (int) schedulerLogic.time(event.getRetrycount());

        String sqlnext= """
                UPDATE events
                SET next_attempt_at=created_at+
                """
    }
    public int getRetryCount(EventRequest event){
        String sqlcount= """
                SELECT retry_count
                FROM events
                WHERE event_id=?
                """;
        int time =jdbcTemplate.queryForObject(sqlcount,Integer.class,event.getEventId());
        return time;
    }

}

