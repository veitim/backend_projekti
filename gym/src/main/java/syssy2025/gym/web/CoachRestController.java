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

import syssy2025.gym.domain.Coach;
import syssy2025.gym.domain.CoachRepository;

@RestController
@RequestMapping("/api/coaches")
public class CoachRestController {

    private final CoachRepository coaRepository;

    public CoachRestController(CoachRepository coaRepository) {
        this.coaRepository = coaRepository;
    }

    @GetMapping
    public List<Coach> getAllCoaches(){
        return (List<Coach>) coaRepository.findAll();
    }

    @GetMapping("/{id}")
	public @ResponseBody Optional<Coach> findCoachRest(@PathVariable("id") Long coach_id) {	
		return coaRepository.findById(coach_id);
	} 

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<Coach> createCoachRest(@RequestBody Coach coach) {
        Coach saved = coaRepository.save(coach);
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
	public @ResponseBody List<Coach> deleteCoach(@PathVariable("id") Long coach_id) {	
    	coaRepository.deleteById(coach_id);
		return (List<Coach>) coaRepository.findAll();
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
	public  ResponseEntity<Coach> updateCoach(@PathVariable("id") Long coach_id, @RequestBody Coach coach) {
		coach.setCoach_id(coach_id);
		Coach uCoach = coaRepository.save(coach);
		return ResponseEntity.ok(uCoach);
	}
}
