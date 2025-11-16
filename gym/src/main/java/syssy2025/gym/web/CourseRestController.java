package syssy2025.gym.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import syssy2025.gym.domain.Course;
import syssy2025.gym.domain.CourseRepository;

@RestController
@RequestMapping("api/courses")
public class CourseRestController {

    private final CourseRepository couRepository;

    public CourseRestController(CourseRepository couRepository) {
        this.couRepository = couRepository;
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return (List<Course>) couRepository.findAll();
    }

    @GetMapping("/{id}")
	public @ResponseBody Optional<Course> findCourseRest(@PathVariable("id") Long course_id) {	
		return couRepository.findById(course_id);
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Course> createCourseRest(@RequestBody Course course) {
        Course saved = couRepository.save(course);
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
	public @ResponseBody List<Course> deleteCourse(@PathVariable("id") Long course_id) {	
    	couRepository.deleteById(course_id);
		return (List<Course>) couRepository.findAll();
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
	public  ResponseEntity<Course> updateCoach(@PathVariable("id") Long course_id, @RequestBody Course course) {
		course.setCourse_id(course_id);
		Course uCourse = couRepository.save(course);
		return ResponseEntity.ok(uCourse);
	}
}
