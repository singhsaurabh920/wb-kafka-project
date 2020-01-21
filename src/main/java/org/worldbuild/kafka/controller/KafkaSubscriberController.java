package org.worldbuild.kafka.controller;

import lombok.extern.log4j.Log4j2;
import org.worldbuild.kafka.modal.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/kafka/suscribe")
public class KafkaSubscriberController {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public Mono<UserDto> userSubscriberHandler(){
        return Mono.just(new UserDto());
    }
}
