package com.caa.spring.mongo.api.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.caa.spring.mongo.api.model.Course;


public interface CourseRepository extends MongoRepository<Course, Integer>{
	
}

