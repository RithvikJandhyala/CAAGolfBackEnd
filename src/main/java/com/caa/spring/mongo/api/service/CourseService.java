package com.caa.spring.mongo.api.service;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.caa.spring.mongo.api.model.Match;
import com.caa.spring.mongo.api.model.MatchDaySummary;
import com.caa.spring.mongo.api.model.Player;
import com.caa.spring.mongo.api.model.School;
import com.caa.spring.mongo.api.model.Team;
import com.caa.spring.mongo.api.model.Course;
import com.caa.spring.mongo.api.model.Event;
import com.caa.spring.mongo.api.repository.MatchRepository;
import com.caa.spring.mongo.api.repository.PlayerRepository;
import com.caa.spring.mongo.api.repository.TeamRepository;
import com.caa.spring.mongo.api.repository.UserRepository;
import com.caa.spring.mongo.api.repository.MatchDaySummaryRepository;
import com.caa.spring.mongo.api.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository repository;
	public List<Course> getCourses(){
		return repository.findAll();
	}
	public String saveCourse(Course gc) { 
		
		int id;
		List <Course> courses = getCourses();
		if(courses.size() == 0) {
			id = 1;
		}
		else {
			Course maxIDCourse = courses.stream()
	                .max(Comparator.comparingInt(Course::getId))
	                .get();
			int maxID = maxIDCourse.getId();
			id = maxID +1;
		}
		gc.setId(id);
		repository.save(gc);
		return "Added " + gc.getName()+" With ID " + gc.getId();
    }
	public String deleteCourse(int id) {
		repository.deleteById(id);
		return "Course deleted with  " + id;
	}
}