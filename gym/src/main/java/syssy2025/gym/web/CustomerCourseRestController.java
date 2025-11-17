package syssy2025.gym.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import syssy2025.gym.domain.CustomerCourse;
import syssy2025.gym.domain.CustomerCourseRepository;

@RestController
@RequestMapping("/api/customercourses")
public class CustomerCourseRestController {

    private final CustomerCourseRepository cucouRepository;

    public CustomerCourseRestController(CustomerCourseRepository cuouRepository) {
        this.cucouRepository = cuouRepository;
    }

    @GetMapping
    public List<CustomerCourse> getAllCustomerCourses(){
        return (List<CustomerCourse>) cucouRepository.findAll();
    }

    @GetMapping("/{id}")
	public @ResponseBody Optional<CustomerCourse> findCustomerCourseRest(@PathVariable("id") Long customercourse_id) {	
		return cucouRepository.findById(customercourse_id);
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CustomerCourse> createCustomerCourseRest(@RequestBody CustomerCourse customercourse) {
        CustomerCourse saved = cucouRepository.save(customercourse);
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
	public @ResponseBody List<CustomerCourse> deleteCustomerCourse(@PathVariable("id") Long customercourse_id) {	
    	cucouRepository.deleteById(customercourse_id);
		return (List<CustomerCourse>) cucouRepository.findAll();
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
	public ResponseEntity<CustomerCourse> updateCustomerCourse(@PathVariable("id") Long customercourse_id, @RequestBody CustomerCourse customercourse) {
		customercourse.setCustomercourse_id(customercourse_id);
		CustomerCourse uCCourse = cucouRepository.save(customercourse);
		return ResponseEntity.ok(uCCourse);
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<CustomerCourse> patchCustomerCourse(@Valid @PathVariable Long customercourse_id, @RequestBody CustomerCourse patched) {
        return cucouRepository.findById(customercourse_id)
            .map(customercourse -> {
                customercourse.setPaidstatus(patched.isPaidstatus());

                CustomerCourse saved = cucouRepository.save(customercourse);
                return ResponseEntity.ok(saved);
            })
            .orElse(ResponseEntity.notFound().build());
    }

}
