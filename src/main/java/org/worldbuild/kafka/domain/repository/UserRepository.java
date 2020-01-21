package org.worldbuild.kafka.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.worldbuild.kafka.domain.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User ,String> {

    Optional<User> findByUsername(String username);
}
