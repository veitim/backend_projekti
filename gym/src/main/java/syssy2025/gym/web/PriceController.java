package syssy2025.gym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import syssy2025.gym.domain.Price;
import syssy2025.gym.domain.PriceRepository;


@Controller
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceRepository pRepository;

    @GetMapping
    public String getPriceList(Model model) {
        model.addAttribute("prices", pRepository.findAll());
        return "prices";
    }
    
    @GetMapping("/add")
    public String addPrice(Model model) {
        model.addAttribute("price", new Price());
        return "addprice";
    }

    @PostMapping("/save")
    public String savePrice(Price price) {
        pRepository.save(price); 
        return "redirect:/prices";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deletePrice(@PathVariable("id") Long price_id, Model model) {
        pRepository.deleteById(price_id);
        return "redirect:/prices";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editPrice(@PathVariable("id") Long price_id, Model model) {
        model.addAttribute("price", pRepository.findById(price_id));
        return "editprice";
    }
}
