package syssy2025.gym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import syssy2025.gym.domain.Course;
import syssy2025.gym.domain.CourseRepository;


@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository couRepository;

    @GetMapping
    public String courseList(Model model) {
        model.addAttribute("courses", couRepository.findAll());
        return "courses";
    }

    @GetMapping("/add")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "addcourse";
    }
    
    @PostMapping("/save")
    public String saveCourse(Course course) {
        couRepository.save(course); 
        return "redirect:/courses";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long course_id, Model model) {
        couRepository.deleteById(course_id);
        return "redirect:/courses";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable("id") Long course_id, Model model) {
        model.addAttribute("course", couRepository.findById(course_id));
        return "editcourse";
    }
}
