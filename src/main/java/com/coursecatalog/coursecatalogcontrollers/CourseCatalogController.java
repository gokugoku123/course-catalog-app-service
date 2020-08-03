package com.coursecatalog.coursecatalogcontrollers;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.coursecatalog.models.Course;
import com.coursecatalog.models.Student;

@RestController
@RequestMapping("/catalog")
public class CourseCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	************************************************ Course Requests ********************************************
//	**********************************Test request
	@RequestMapping("/hi")
	public String sayhi() {
		return "HELLO";
	}
	
//************************************************** get all Courses
	@RequestMapping("/courses")
	public List<Course> getAllCourses() {
		
		String url = "http://COURSE-INFO-APPLICATION/courseinfo/courses";
		ResponseEntity<Course[]> entity = restTemplate.getForEntity(url, Course[].class);
		
		return entity.getBody() != null ? Arrays.asList(entity.getBody()) : Collections.emptyList();
	}
	
//	//************************************************** get a course by id
	@RequestMapping("/courses/{cId}")
	public Course getCourse(@PathVariable String cId) {
		String url = "http://COURSE-INFO-APPLICATION/courseinfo/courses/" + cId;
		return restTemplate.getForObject(url, Course.class);
	}
	
	//************************************************** get enrolled students in a  course by the course id
	@RequestMapping("/courses/{cId}/enrolledStudents")
	public List<Student> getEnrolledStudents(@PathVariable String cId) {

		//************************************************** getting a String list of student id's from the course app
		String url = "http://COURSE-INFO-APPLICATION/courseinfo/courses/" + cId + "/studentIdList";
		ResponseEntity<String[]> entity = restTemplate.getForEntity(url, String[].class);
		
		if(entity.getBody() != null) {
			
			//************************************************** get the list of students from the String list of student ids			
			url = "http://STUDENT-INFO-APPLICATION/sinfo/students/studentsList";
			ResponseEntity<Student[]> studentsEntity = restTemplate.postForEntity(url, entity.getBody(), Student[].class);
			
			if(studentsEntity.hasBody()) {
				return Arrays.asList(studentsEntity.getBody());
			}
		}
		return Collections.emptyList();
	}
	
	
//	//************************************************** Create a new course
	@RequestMapping(method=RequestMethod.POST, value="/courses")
	public void addCourse(@RequestBody Course course ) {
		String url = "http://COURSE-INFO-APPLICATION/courseinfo/courses";
		restTemplate.postForObject(url, course, Course.class);
	}
	
	
//	//************************************************** update a existing course
	@RequestMapping(method=RequestMethod.PUT, value="/courses/{cId}")
	public void updateCourse(@RequestBody Course course, String cId ) {
		String url = "http://COURSE-INFO-APPLICATION/courseinfo/courses/" + cId;
		restTemplate.put(url, course);
	}
	
//	//************************************************** enroll a student in a  course
	@RequestMapping("/courses/addenroll/{cId}/{sId}")
	public String enrollStudent(@PathVariable String cId, @PathVariable String sId) {

		String url = "http://COURSE-INFO-APPLICATION/courseinfo/courses/add/" + cId + "/" + sId;
		restTemplate.put(url, null);
		return "Enroll Successful";
	}
	
	//************************************************** de enroll a student from a course
	@RequestMapping("/courses/removeenroll/{cId}/{sId}")
	public String deEnrollStudent(@PathVariable String cId, @PathVariable String sId) {
		String url = "http://COURSE-INFO-APPLICATION/courseinfo/courses/delete/" + cId + "/" + sId;
		restTemplate.put(url, null);
		return "DeEnroll Successful";
	}
	
	
//	//************************************************** Delete a course
	@RequestMapping(method=RequestMethod.DELETE, value="/courses/{cId}")
	public void deleteCourse(@PathVariable String cId ) {
		String url = "http://COURSE-INFO-APPLICATION/courseinfo/courses/" + cId;
		restTemplate.delete(url, cId);
	}
	
	
	
	
//	************************************************ Student Requests ********************************************
//	similar to above methods in course 
//	sinfo - studentinfo
	
	@RequestMapping("/students")
	public List<Student> getAllStudents() {
		
		String url = "http://STUDENT-INFO-APPLICATION/sinfo/students";
		ResponseEntity<Student[]> entity = restTemplate.getForEntity(url, Student[].class);
		
		return entity.getBody() != null ? Arrays.asList(entity.getBody()) : Collections.emptyList();
	}
	
	@RequestMapping("/students/{sId}")
	public Student getStudent(@PathVariable String sId) {
		String url = "http://STUDENT-INFO-APPLICATION/sinfo/students/" + sId;
		return restTemplate.getForObject(url, Student.class);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/students")
	public void addStudent(@RequestBody Student student ) {
		String url = "http://STUDENT-INFO-APPLICATION/sinfo/students";
		restTemplate.postForObject(url, student, Student.class);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/students/{sId}")
	public void updateStudent(@RequestBody Student student, String sId ) {
		String url = "http://STUDENT-INFO-APPLICATION/sinfo/students/" + sId;
		restTemplate.put(url, student);
	}
	
	@DeleteMapping("/students/{sId}")
	public void deleteStudent(@PathVariable String sId ) {
		String url = "http://STUDENT-INFO-APPLICATION/sinfo/students/" + sId;
		restTemplate.delete(url, sId);
	}
	
}
