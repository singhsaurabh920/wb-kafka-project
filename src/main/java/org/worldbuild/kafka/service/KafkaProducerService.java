package org.worldbuild.kafka.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.worldbuild.kafka.constnat.KafkaConstant;
import org.worldbuild.kafka.modal.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
@Log4j2
@Service
public class KafkaProducerService {
   @Autowired
   private KafkaTemplate kafkaTemplate;

    public void produceUser(UserDto userDto) {
            ListenableFuture<SendResult<String, UserDto>> future = kafkaTemplate.send(KafkaConstant.User.TOPIC, userDto);
            future.addCallback( new ListenableFutureCallback<SendResult<String, UserDto>>() {
                @Override
                public void onSuccess(SendResult<String, UserDto> userSendResult) {
                    log.info("SENT - " + userDto);
                }
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("SENT " + userDto + " FAILED DUE TO : " + throwable.getMessage());
                }
            });
    }

}
