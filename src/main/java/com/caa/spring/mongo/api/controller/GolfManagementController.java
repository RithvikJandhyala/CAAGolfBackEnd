package com.caa.spring.mongo.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.caa.spring.mongo.api.model.Course;
import com.caa.spring.mongo.api.model.Event;
import com.caa.spring.mongo.api.model.EventScoring;
import com.caa.spring.mongo.api.model.User;
import com.caa.spring.mongo.api.model.Player;
import com.caa.spring.mongo.api.model.School;
import com.caa.spring.mongo.api.service.PlayerService;
import com.caa.spring.mongo.api.service.SchoolService;
import com.caa.spring.mongo.api.service.UserService;
import com.caa.spring.mongo.api.service.EventService;
import com.caa.spring.mongo.api.service.CourseService;

@CrossOrigin(origins = "https://www.azcaagolf.com")
@RestController
public class GolfManagementController {
	@Autowired
	private PlayerService playerService;
	@Autowired
	private UserService userService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private EventService eventService;
	@Autowired
	private CourseService courseService;
	@PostMapping("/addPlayer")
	public String savePlayer(@RequestBody Player player) {
		return playerService.savePlayer(player);
	}
	
	@GetMapping("/findAllPlayers")
	public List<Player> getPlayers(){
		return playerService.getPlayers();
	}
	
	@GetMapping("/findPlayersBySchool/{school}")
	public List<Player> getPlayersBySchool(@PathVariable String school){
		return playerService.getPlayersBySchool(school, Sort.by(Sort.Direction.ASC, "playerID"));
	}
	@GetMapping("/findPlayersBySchoolAndDivision/{school}/{division}")
	public List<Player> getPlayersBySchoolAndDivision(@PathVariable String school, @PathVariable String division){
		return playerService.getPlayersBySchoolAndDivision(school,division);
	}
	@GetMapping("/findPlayersByDivision/{division}")
	public List<Player> getPlayersByDivision(@PathVariable String division){
		return playerService.getPlayersByDivision(division);
	}
	
	
	@GetMapping("/findAllPlayers/{id}")
	public Optional<Player> getPlayer(@PathVariable int id){
		return playerService.getPlayer(id);
	}
	
	@GetMapping("/deletePlayer/{id}")
	public String deletePlayer(@PathVariable int id) {
		return playerService.deletePlayer(id);
	}
	@GetMapping("/resetPlayerScores")
	public String resetPlayerScores() {
		return playerService.resetPlayerScores();
	}
	
	@PostMapping("/addEvent")
	public String saveEvent(@RequestBody Event event) {
		//return playerService.saveMatch(match);
		return eventService.saveEvent(event);
	}
	@GetMapping("/deleteEvent/{id}")
	public String deleteEvent(@PathVariable int id) {
		//return playerService.saveMatch(match);
		return eventService.deleteEvent(id);
	}
	@GetMapping("/findAllEvents")
	public List<Event> getEvent(){
		return eventService.getEvents();
	}
	@GetMapping("/findAllEventScorings")
	public List<EventScoring> getEventScorings(){
		return eventService.getEventScorings();
	}
	@GetMapping("/findAllEventScorings/{division}")
	public List<EventScoring> getEventScoringsByDivision(@PathVariable String division){
		return eventService.getEventScoringsByDivision(division);
	}
	@GetMapping("/findAllEventScoringsByEventSchool/{eventID}/{school}")
	public List<EventScoring> getEventScoringsByEventSchool(@PathVariable int eventID, @PathVariable String school){
		return eventService.getEventScoringsByEventSchool(eventID,school);
	}
	@GetMapping("/findAllEventsBySchool/{school}")
	public List<Event> getEventsBySchool(@PathVariable String school){
		return eventService.getEventsBySchool(school);
	}
	@GetMapping("/getSignedUpEventIDsBySchool/{school}")
	public List<Integer> getSignedUpEventIDsBySchool(@PathVariable String school){
		return eventService.getSignedUpEventIDsBySchool(school);
	}
	
	@PostMapping("/addEventScorings")
	public String saveEventScorings(@RequestBody List<EventScoring> eventScorings) {
		System.out.println("hello");
		return eventService.saveEventScorings(eventScorings);
		
	}
	
	@PostMapping("/updateScores")
	public String updateEventScorings(@RequestBody List<EventScoring> eventScorings) {
		System.out.println("In update scores");
		return eventService.updateEventScorings(eventScorings);
		
	}
	@GetMapping("/deleteEventScoring/{id}")
	public String deleteEventScoring(@PathVariable String id) {
		//return playerService.saveMatch(match);
		return eventService.deleteEventScoring(id);
	}
	@GetMapping("/getSignedUpPlayers/{eventID}/{school}")
	public List<Integer> getSignedUpPlayers(@PathVariable int eventID,@PathVariable String school) {
		//return playerService.saveMatch(match);
		return eventService.getSignedUpPlayers(eventID,school);
	}
	
	@PostMapping("/addSingleEventScoring")
	public String saveSingleEventScoring(@RequestBody EventScoring eventScoring) {
		System.out.println("hello");
		return eventService.saveSingleEventScoring(eventScoring);
		
	}
	/*@PostMapping("/updateScores")
	public String updateScores(@RequestBody List<EventScoring> eventScorings) {
		//System.out.println("hello");
		return eventService.updateScores(eventScorings);
		
	}*/
	
	
	@PostMapping("/addCourse")
	public String saveCourse(@RequestBody Course course) {
		//return playerService.saveMatch(match);
		return courseService.saveCourse(course);

	}

	@GetMapping("/findAllCourses")
	public List<Course> getCourse(){
		return courseService.getCourses();
	}
	
	@GetMapping("/deleteCourse/{id}")
	public String deleteCourse(@PathVariable int id) {
		return courseService.deleteCourse(id);
	}
	
	
	@GetMapping("/findAllUsers")
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@PostMapping("/userlogin")
	public Optional<User> authenticateUser(@RequestBody User user){
		return userService.authenticateUser(user);
	}
	
	@PostMapping("/addUser")
	public String saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	@GetMapping("/deleteUser/{username}")
	public String deleteUser(@PathVariable String username) {
		return userService.deleteUser(username);
	}
	@GetMapping("/findAllSchools")
	public List<School> getSchools(){
		return schoolService.getSchools();
	}

	@GetMapping("/deleteSchool/{id}")
	public String deleteSchool(@PathVariable int id) {
		return schoolService.deleteSchool(id);
	}
	
	@PostMapping("/schools/add")
	public int addSchool(@RequestParam("name") String name,
		 @RequestParam("image") MultipartFile image, Model model
		 ) 
	  throws IOException {
	    int id = schoolService.addSchool(name, image);
	    return id;
	}
	
	@GetMapping("/schools/{id}")
	public School getSchool(@PathVariable int id){ 
		School school = schoolService.getSchool(id);
	    return school;		
	}
	@GetMapping("/resetSeason")
	public String resetSeason() {
		playerService.clear();
		eventService.clear();
		return "Season reset";
	}
	
	@GetMapping("/findAvgScores")
	public String getPlayerAverageScores(){
		return eventService.updatePlayerAverageScores();
	}
	
	
	
	
	
}
