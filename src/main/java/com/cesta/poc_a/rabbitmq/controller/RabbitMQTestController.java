package com.cesta.poc_a.rabbitmq.controller;

import com.cesta.poc_a.rabbitmq.dto.CustomerDto;
import com.cesta.poc_a.rabbitmq.service.EnqueueDequeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQTestController {

    private final EnqueueDequeService enqueueDequeService;

    public RabbitMQTestController(EnqueueDequeService enqueueDequeService) {
        this.enqueueDequeService = enqueueDequeService;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomerDto customerDto) {
        enqueueDequeService.publishMessage(customerDto);
        return "message saved";
    }
}