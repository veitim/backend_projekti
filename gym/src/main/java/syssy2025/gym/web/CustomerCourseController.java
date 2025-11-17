package syssy2025.gym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import syssy2025.gym.domain.CustomerCourse;
import syssy2025.gym.domain.CustomerCourseRepository;

@Controller
@RequestMapping("/customercourses")
public class CustomerCourseController {

    @Autowired
    private CustomerCourseRepository cucouRepository;

    @GetMapping
    public String customercourseList(Model model) {
        model.addAttribute("customercourses", cucouRepository.findAll());
        return "customercourses";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/findcuscourse/{id}")
    public String findCourse(@PathVariable("id") Long customercourse_id, Model model) {
        model.addAttribute("customercourse", cucouRepository.findById(customercourse_id));
        return "findcuscourse";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String addCustomerCourse(Model model) {
        model.addAttribute("customercourse", new CustomerCourse());
        return "addcustomercourse";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public String saveCustomerCourse(CustomerCourse customercourse) {
        cucouRepository.save(customercourse); 
        return "redirect:/customercourses";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCustomerCourse(@PathVariable("id") Long customercourse_id, Model model) {
        cucouRepository.deleteById(customercourse_id);
        return "redirect:/customercourses";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editCustomerCourse(@PathVariable("id") Long customercourse_id, Model model) {
        model.addAttribute("course", cucouRepository.findById(customercourse_id));
        return "editcustomercourse";
    }
}
