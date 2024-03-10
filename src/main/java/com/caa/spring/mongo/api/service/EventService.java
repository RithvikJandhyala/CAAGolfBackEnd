package com.caa.spring.mongo.api.service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caa.spring.mongo.api.model.Event;
import com.caa.spring.mongo.api.model.EventScoring;
import com.caa.spring.mongo.api.model.Match;
import com.caa.spring.mongo.api.model.Player;
import com.caa.spring.mongo.api.model.School;
import com.caa.spring.mongo.api.model.Team;
import com.caa.spring.mongo.api.repository.EventRepository;
import com.caa.spring.mongo.api.repository.EventScoringRepository;
import com.caa.spring.mongo.api.repository.PlayerRepository;
import com.caa.spring.mongo.api.repository.SchoolRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository repository;
	@Autowired
	private EventScoringRepository eventScoringRepository;
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private SchoolRepository schoolRepository;
	public List<Event> getEvents(){
		return repository.findAll();
	}
	
	public String saveEvent(Event event) {
		long id;
		if(repository.count() == 0) {
			id=1;
		}
		else {
			List<Event> events = repository.findAll();
			Event maxIDEvent = events.stream()
	                .max(Comparator.comparingLong(Event::getId))
	                .get();
			long maxID = maxIDEvent.getId();
			id = maxID + 1;
		}
		event.setId(id);
		event.setSlots(event.getTeeTimes());
		repository.save(event);
		return "Added Event " + event.getId();
	}
	public String deleteEvent(long id) {
		repository.deleteById(id);
		List<EventScoring> eventScorings = eventScoringRepository.findEventScoringByEventID((int)id);
	    for (EventScoring eventScoring : eventScorings) {
	        eventScoringRepository.delete(eventScoring);
	    }
		System.out.print("delete " + id);
		return "Event deleted with " + id;
	}
	
	public String saveEventScorings(List<EventScoring> eventScorings) {
		int eventID = eventScorings.get(0).getEventID();
		Event event = repository.findById((long) eventID).get();
		event.setSlots(event.getSlots()-eventScorings.size());
		repository.save(event);
		eventScoringRepository.saveAll(eventScorings);
		return "Event Scorings succesfully saved";
	}
	
	public String updateEventScorings(List<EventScoring> eventScorings) {
		System.out.println("Event Scorings succesfully updated");
		eventScoringRepository.saveAll(eventScorings);
		return "Event Updated succesfully saved";
	}
	
	
	
	
	
	public String saveSingleEventScoring(EventScoring eventScoring) {
		System.out.println("Reached SEC");
		eventScoringRepository.save(eventScoring);
		return "Event Scorings succesfully saved";
	}
	public List<EventScoring> getEventScorings(){
		List<EventScoring> eventScorings = eventScoringRepository.findAll();
		List<Event> events = repository.findAll();
		List<Player> players = playerRepository.findAll();
		
		for (EventScoring eventScoring : eventScorings) {
		    Optional<Event> optionalEvent = events.stream()
		            .filter(event -> eventScoring.getEventID() == event.getId())
		            .findFirst();
		    optionalEvent.ifPresent(event -> {
		        eventScoring.setEventDate(event.getEventDate());
		        eventScoring.setCourse(event.getGolfCourse());
		    });
		        
	        Optional<Player> optionalPlayer = players.stream()
		            .filter(player -> eventScoring.getPlayerID() == player.getPlayerID())
		            .findFirst();
	        optionalPlayer.ifPresent(player -> {
		        eventScoring.setPlayerDivision(player.getDivision());
		        eventScoring.setPlayerSchool(player.getSchool());
		        eventScoring.setPlayerName(player.getName());
		        
		       
		       
		    });
		}
		
		return eventScorings;
	}
	public List<EventScoring>  getEventScoringsByDivision(String division){
		if(division.equals("All Divisions")) {
			return getEventScorings();
		}
		else {
			List<EventScoring> eventScorings = getEventScorings();
			List<EventScoring> eventScoringsByDivision = eventScorings.stream()
			        .filter(eventScoring -> eventScoring.getPlayerDivision().equals(division))
			        .collect(Collectors.toList());
			return eventScoringsByDivision;
			
			
		}
	}
	
	
	public List<Event> getEventsBySchool(String desiredSchoolName){
		List<EventScoring> eventScorings = eventScoringRepository.findAll();
		School school = schoolRepository.findByName(desiredSchoolName);
		int schoolID =  school.getID();
		List<Event> events = repository.findAll();
		List<Event> eventsBySchool = new ArrayList<>();
		Set<Event> eventsBySchoolSet = new HashSet<>();
	    for (EventScoring eventScoring : eventScorings) {
	            if (eventScoring.getPlayerID() >= schoolID && eventScoring.getPlayerID() < schoolID + 1000 && eventScoring.getPlayerScore() == 0) {
	                Optional<Event> optionalEvent = events.stream()
	                        .filter(event -> event.getId() == eventScoring.getEventID())
	                        .findFirst();
	                optionalEvent.ifPresent(eventsBySchoolSet::add);
	            }
	     }
	    eventsBySchool.addAll(eventsBySchoolSet);  
		return eventsBySchool;
	}
	
	public List<Integer> getSignedUpEventIDsBySchool(String desiredSchoolName){
		List<EventScoring> eventScorings = eventScoringRepository.findAll();
		School school = schoolRepository.findByName(desiredSchoolName);
		int schoolID =  school.getID();
		List<Event> events = repository.findAll();
		Set<Integer> eventIdsBySchoolSet = new HashSet<>();
	    for (EventScoring eventScoring : eventScorings) {
	        if (eventScoring.getPlayerID() >= schoolID && eventScoring.getPlayerID() < schoolID + 1000) {
	            Optional<Event> optionalEvent = events.stream()
	                    .filter(event -> event.getId() == eventScoring.getEventID())
	                    .findFirst();
	            optionalEvent.ifPresent(event -> eventIdsBySchoolSet.add((int)event.getId()));
	        }
	    }
	    return new ArrayList<>(eventIdsBySchoolSet);
	}

	public List<EventScoring> getEventScoringsByEventSchool(int eventID, String desiredSchoolName){
		School school = schoolRepository.findByName(desiredSchoolName);
		int schoolID =  school.getID();
		List<EventScoring> eventScorings = getEventScorings();
		List<EventScoring> eventScoringsByEvent = eventScorings.stream()
			    .filter(event -> event.getEventID() == eventID)
			    .collect(Collectors.toList());
		List<EventScoring> eventScoringsByEventSchool = new ArrayList<>();
		for (EventScoring eventScoring : eventScoringsByEvent) {
            if (eventScoring.getPlayerID() >= schoolID && eventScoring.getPlayerID() < schoolID + 1000) {
            	eventScoringsByEventSchool.add(eventScoring);
            }
		}
		return eventScoringsByEventSchool;
	}
	public List<Integer> getSignedUpPlayers(int eventID,String desiredSchoolName){
		School school = schoolRepository.findByName(desiredSchoolName);
		int schoolID =  school.getID();
		List<EventScoring> eventScoringsByEvent = eventScoringRepository.findEventScoringByEventID(eventID);
		List<Integer> playerIDs =  new ArrayList<>();
		for (EventScoring eventScoring : eventScoringsByEvent) {
	        if (eventScoring.getPlayerID() >= schoolID && eventScoring.getPlayerID() < schoolID + 1000) {
	        	playerIDs.add(eventScoring.getPlayerID());
	        }
		}
		return playerIDs;
	}
	public String deleteEventScoring(String id) {
		EventScoring eventScoring = eventScoringRepository.findById(id).get();
		int eventID = eventScoring.getEventID();
		Event event = repository.findById((long) eventID).get();
		event.setSlots(event.getSlots()+1);
		repository.save(event);
		eventScoringRepository.deleteById(id);
		return "Event Scoring " + id + " Deleted";
	}
	public String clear() {
		repository.deleteAll();
		eventScoringRepository.deleteAll();
		return "ALL EVENTS AND EVENT SCORINGS CLEARED";
	}
	
	
}
