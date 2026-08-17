package com.aditya.demo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DestinationService {
    private final RestClient restClient;
    public DestinationService(RestClient restClient){
        this.restClient=restClient;
    }
    public void deliver(EventRequest event){
        restClient.post()
                .uri(event.getDestinationUrl())
                .header("Content-type","application/json")
                .body(event.getPayload())
                .retrieve()
                .toBodilessEntity();

    }


}
