package com.caa.spring.mongo.api.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Team")
public class Team {	
	@Id
	private String id;
	private String name;
	private int wins;
	private int losses;
	private int ties;
	private double pct;
	
	private int winPoints = 0;
	private int lossPoints = 0;
	private String division;
	
	public Team(String id, String name, int wins, int losses,int ties, double pct, int winPoints, int lossPoints,  String division) {
		super();
		this.id = id;
		this.name = name;
		this.wins = wins;
		this.losses = losses;
		this.ties = ties;
		this.pct = pct;
		this.winPoints = winPoints;
		this.lossPoints = lossPoints;
		this.division = division;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getTies() {
		return ties;
	}
	public void setTies(int ties) {
		this.ties = ties;
	}
	public int getWinPoints() {
		return winPoints;
	}
	public void setWinPoints(int winPoints) {
		this.winPoints = winPoints;
	}
	public int getLossPoints() {
		return lossPoints;
	}
	public void setLossPoints(int lossPoints) {
		this.lossPoints = lossPoints;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public double getPct()
	{
		return pct;
	}
	public void setPct(double pct) {
		this.pct = pct;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", wins=" + wins + ", losses=" + losses + ", ties=" + ties
				+ ", pct=" + pct + ", winPoints=" + winPoints + ", lossPoints=" + lossPoints +  ", division="
				+ division + "]";
	}
}
