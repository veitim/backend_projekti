package syssy2025.gym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import syssy2025.gym.domain.CoachRepository;
import syssy2025.gym.domain.MartialArt;
import syssy2025.gym.domain.MartialArtRepository;

@Controller
@RequestMapping("/martialarts")
public class MartialartController {

    @Autowired
    private MartialArtRepository maRepository;

    @Autowired
    private CoachRepository coaRepository;

    @GetMapping
    public String martialartList(Model model) {
        model.addAttribute("martialarts", maRepository.findAll());
        return "martialarts";
    }

    @GetMapping("/add")
    public String addMartialart(Model model) {
        model.addAttribute("martialart", new MartialArt());
        model.addAttribute("coaches", coaRepository.findAll());
        return "addmartialart";
    }

    @PostMapping("/save")
    public String saveMartialArt(MartialArt martialart) {
        maRepository.save(martialart); 
        return "redirect:/martialarts";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteMartialArt(@PathVariable("id") Long martialart_id, Model model) {
        maRepository.deleteById(martialart_id);
        return "redirect:/martialarts";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editMartialArt(@PathVariable("id") Long martialart_id, Model model) {
        model.addAttribute("martialart", maRepository.findById(martialart_id));
        model.addAttribute("coaches", coaRepository.findAll());
        return "editmartialart";
    }

}
