package com.caa.spring.mongo.api.model;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Event")
public class Event {
	private long id;
	private String eventDate;
	private String time;
	private String golfCourse;
	private String division;
	private String hostSchool;
	private int teeTimes;
	private int slots;
	public double fee;
	public Event(long id,String eventDate, String time, String golfCourse,String hostSchool,int teeTimes,int slots,String division, double fee) {
		this.id = id;
		this.eventDate = eventDate;
		this.time = time;
		this.golfCourse = golfCourse;
		this.hostSchool = hostSchool;
		this.teeTimes = teeTimes;
		this.slots = slots;
		this.division = division;
		this.fee = fee;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getGolfCourse() {
		return golfCourse;
	}
	public void setGolfCourse(String golfCourse) {
		this.golfCourse = golfCourse;
	}
	public String getHostSchool() {
		return hostSchool;
	}
	public void setHostSchool(String hostSchool) {
		this.hostSchool = hostSchool;
	}
	public int getTeeTimes() {
		return teeTimes;
	}
	public void setTeeTimes(int teeTimes) {
		this.teeTimes = teeTimes;
	}
	public int getSlots() {
		return slots;
	}
	public void setSlots(int slots) {
		this.slots = slots;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}

}
