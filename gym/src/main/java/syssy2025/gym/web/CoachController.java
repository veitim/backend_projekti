package syssy2025.gym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import syssy2025.gym.domain.Coach;
import syssy2025.gym.domain.CoachRepository;

@Controller
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    private CoachRepository coaRepository;

    @GetMapping
    public String coachList(Model model) {
        model.addAttribute("coaches", coaRepository.findAll());
        return "coaches";
    }

    @GetMapping("/add")
    public String addCoach(Model model) {
        model.addAttribute("coach", new Coach());
        return "addcoach";
    }

    @PostMapping("/save")
    public String saveCoach(Coach coach) {
        coaRepository.save(coach); 
        return "redirect:/coaches";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteCoach(@PathVariable("id") Long coach_id, Model model) {
        coaRepository.deleteById(coach_id);
        return "redirect:/coaches";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editCoach(@PathVariable("id") Long coach_id, Model model) {
        model.addAttribute("coach", coaRepository.findById(coach_id));
        return "editcoach";
    }

}
