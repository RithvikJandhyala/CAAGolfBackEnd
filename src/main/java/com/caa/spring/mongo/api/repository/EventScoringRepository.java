package com.caa.spring.mongo.api.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.caa.spring.mongo.api.model.EventScoring;


public interface EventScoringRepository extends MongoRepository<EventScoring, String>{
	List<EventScoring> findEventScoringByEventID(int eventID);	
	void deleteByEventID(int eventID);
	List<EventScoring> findEventScoringByPlayerDivision(String division);
	List<EventScoring> findEventScoringByPlayerID(int playerID);
	List<EventScoring> findByEventIDIn(List<Long> eventIDs);
	@Aggregation(pipeline = {
            "{ $group: { _id: '$playerID', totalScore: { $sum: '$playerScore' }, count: { $sum: 1 } } }",
            "{ $project: { playerId: '$_id', averageScore: { $divide: ['$totalScore', '$count'] } } }"
    })
    List<PlayerAverageScore> findPlayerAverageScore();
    interface PlayerAverageScore
    {
        int getPlayerID();
        double getAverageScore();
    }
}
