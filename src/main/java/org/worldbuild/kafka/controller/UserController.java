package org.worldbuild.kafka.controller;

import lombok.extern.log4j.Log4j2;
import org.worldbuild.kafka.domain.entity.User;
import org.worldbuild.kafka.domain.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(KafkaPublisherController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        userRepository.save(user).subscribe();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<User> update(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return  userRepository.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Mono<User>> findById(@PathVariable("id") String id) {
        Mono<User> user = userRepository.findById(id);
        HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<User>>(user, status);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Flux<User> findByName(@PathVariable("name") String name) {
        return userRepository.findByName(name);
    }

    @RequestMapping(value="/" , method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<User> findAll() {
        return userRepository.findAll();
    }





}
