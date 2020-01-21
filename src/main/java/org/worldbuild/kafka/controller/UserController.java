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
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody User user) {
        userRepository.save(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public User update(@RequestBody User user) {
        return userRepository.save(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
          userRepository.deleteById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? new ResponseEntity<>(user.get(), HttpStatus.OK) : new ResponseEntity<>(user.get(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/name/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> findByName(@PathVariable("username") String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() ? new ResponseEntity<>(user.get(), HttpStatus.OK) : new ResponseEntity<>(user.get(), HttpStatus.NOT_FOUND);
    }
}
