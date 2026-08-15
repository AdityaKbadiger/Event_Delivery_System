package com.aditya.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final JdbcTemplate jdbcTemplate;
    public EventService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public String createEvent(EventRequest event){
        String sql ="""
                INSERT INTO events
                (event_id, event_type, destination_url, payload)
                VALUES (?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,event.getEventId(),event.getEventType(),event.getDestinationUrl(),event.getPayload()
        );
        return "Event added successfully";
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
}
