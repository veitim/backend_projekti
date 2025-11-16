package syssy2025.gym.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import syssy2025.gym.domain.AppUser;
import syssy2025.gym.domain.AppUserRepository;

@RestController
@RequestMapping("/api/appusers")
public class AppUserRestController {

    private final AppUserRepository auRepository;

    public AppUserRestController(AppUserRepository auRepository) {
        this.auRepository = auRepository;
    }

    @GetMapping
    public List<AppUser> appuserList() {
        return (List<AppUser>) auRepository.findAll();
    }
}
