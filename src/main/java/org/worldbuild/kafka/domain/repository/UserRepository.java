package org.worldbuild.kafka.domain.repository;

import org.springframework.stereotype.Repository;
import org.worldbuild.kafka.domain.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
@Repository
public interface UserRepository extends ReactiveMongoRepository<User ,String> {

    Flux<User> findByName(String name);
}
