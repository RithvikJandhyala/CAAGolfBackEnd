package com.caa.spring.mongo.api.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EventScoring")
public class EventScoring {
	@Id
	private String id;
	private int eventID;
	private int playerID;
	private int playerScore;
	@Transient
	private String eventDate;
	@Transient
	private String course;
	@Transient
	private String playerDivision;
	@Transient
	private String playerSchool;
	@Transient
	private String playerName;
	
	public EventScoring() {
        // Default constructor
    }
	public EventScoring(String id, int eventID, int playerID, int playerScore) {
		super();
		this.id = id;
		this.eventID = eventID;
		this.playerID = playerID;
		this.playerScore = playerScore;
	}
	public EventScoring(String id, int eventID, int playerID, int playerScore, String eventDate, String course, String playerDivision, String playerSchool,  String playerName) {
		super();
		this.id = id;		
		this.eventID = eventID;
		this.playerID = playerID;
		this.playerScore = playerScore;
		this.eventDate = eventDate;		
		this.course = course;
		this.playerDivision = playerDivision;
		this.playerSchool = playerSchool;
		this.playerName = playerName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getPlayerDivision() {
		return playerDivision;
	}
	public void setPlayerDivision(String playerDivision) {
		this.playerDivision = playerDivision;
	}
	public String getPlayerSchool() {
		return playerSchool;
	}
	public void setPlayerSchool(String playerSchool) {
		this.playerSchool = playerSchool;
	} 
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
}
