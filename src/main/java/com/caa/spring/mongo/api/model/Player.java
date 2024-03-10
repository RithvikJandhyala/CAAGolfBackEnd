package com.caa.spring.mongo.api.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Player")
public class Player {
	@Id
	private int playerID;
	private String name;
	private String school;
	private String division;
	
	private int rank;
	private int wins = 0;
	private int losses = 0;
	private int ties = 0;
	private int pointsWon = 0;
	
	
	public Player(int playerID, String name, String school, String division,  int wins, int losses, int ties, int pointsWon,int rank) {
		this.playerID = playerID;
		this.name = name;
		this.school = school;
		this.division = division;
		
		this.wins = wins;
		this.losses = losses;
		this.ties = ties;
		this.pointsWon = pointsWon;
		this.rank = rank;
	
	}
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", name=" + name + ", school=" + school + ", division=" + division
				+  ", rank=" + rank + ", wins=" + wins + ", losses=" + losses + "]";
	}
	/**
	 * @return the ties
	 */
	public int getTies() {
		return ties;
	}
	/**
	 * @param ties the ties to set
	 */
	public void setTies(int ties) {
		this.ties = ties;
	}
	/**
	 * @return the pointsWon
	 */
	public int getPointsWon() {
		return pointsWon;
	}
	/**
	 * @param pointsWon the pointsWon to set
	 */
	public void setPointsWon(int pointsWon) {
		this.pointsWon = pointsWon;
	}
}
