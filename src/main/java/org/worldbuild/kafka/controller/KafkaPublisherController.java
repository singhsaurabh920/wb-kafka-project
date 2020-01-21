package org.worldbuild.kafka.controller;

import lombok.extern.log4j.Log4j2;
import org.worldbuild.kafka.service.KafkaProducerService;
import org.worldbuild.kafka.modal.Response;
import org.worldbuild.kafka.modal.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/kafka/publish")
public class KafkaPublisherController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Response<String> userProduceHandler(@RequestBody UserDto userDto){
        log.info("UserDto Request - "+userDto);
        kafkaProducerService.produceUser(userDto);
        return  new Response<>("User submitted");
    }

}
