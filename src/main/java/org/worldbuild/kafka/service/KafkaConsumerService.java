package org.worldbuild.kafka.service;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.worldbuild.kafka.constnat.KafkaConstant;
import org.worldbuild.kafka.modal.UserDto;
@Log4j2
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = KafkaConstant.Text.TOPIC, containerFactory="kafkaListenerContainerFactory", groupId = KafkaConstant.Text.CG)
    public void consumeString(String msg){
        log.info("RECEVIED "+ KafkaConstant.User.CG+" ----: "  + msg );
    }

    @KafkaListener(topics =KafkaConstant.User.TOPIC , containerFactory="userKafkaListenerContainerFactory", groupId = KafkaConstant.User.CG)
    public void consumeUser(UserDto user){
        log.info("RECEVIED "+ KafkaConstant.User.CG+" ----: " + user );
    }
}
