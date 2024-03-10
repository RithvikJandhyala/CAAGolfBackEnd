package com.caa.spring.mongo.api.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.caa.spring.mongo.api.model.Event;



public interface EventRepository extends MongoRepository<Event, Long>{

}


