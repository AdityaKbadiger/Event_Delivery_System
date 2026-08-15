package com.aditya.demo;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Hellocontroller {

    private WebhookService webhookService;
    public Hellocontroller(WebhookService webhookService) {
        this.webhookService = webhookService;
    }
    @GetMapping("/")
    public String home(){
        return "hello relayhub";
    }
    @GetMapping("test")
    public String test(){
        return "this is test page";
    }
    @PostMapping("/webhook/github")

    public String post(@Valid @RequestBody Githubwebhook data){
        return webhookService.processWebhook(data);

    }




}

