package com.caa.spring.mongo.api.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.caa.spring.mongo.api.model.EventScoring;


public interface EventScoringRepository extends MongoRepository<EventScoring, String>{
	List<EventScoring> findEventScoringByEventID(int eventID);	
	void deleteByEventID(int eventID);
	List<EventScoring> findEventScoringByPlayerDivision(String division);	
	

}