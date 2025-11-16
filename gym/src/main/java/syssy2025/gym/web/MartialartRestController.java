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

import syssy2025.gym.domain.MartialArt;
import syssy2025.gym.domain.MartialArtRepository;

@RestController
@RequestMapping("/api/martialarts")
public class MartialartRestController {

    private final MartialArtRepository maRepository;

    public MartialartRestController(MartialArtRepository maRepository) {
        this.maRepository = maRepository;
    }

    @GetMapping
    public List<MartialArt> getAllMartialArts(){
        return (List<MartialArt>) maRepository.findAll();
    }

    @GetMapping("/{id}")
	public @ResponseBody Optional<MartialArt> findMartialartRest(@PathVariable("id") Long martialart_id) {	
		return maRepository.findById(martialart_id);
	} 
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<MartialArt> createMartialArtRest(@RequestBody MartialArt martialart) {
        MartialArt saved = maRepository.save(martialart);
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
	public @ResponseBody List<MartialArt> deleteMartialArt(@PathVariable("id") Long martialart_id) {	
    	maRepository.deleteById(martialart_id);
		return (List<MartialArt>) maRepository.findAll();
	}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
	public  ResponseEntity<MartialArt> updateMartialArt(@PathVariable("id") Long martialart_id, @RequestBody MartialArt martialart) {
		martialart.setMartialart_id(martialart_id);
		MartialArt uMartialart = maRepository.save(martialart);
		return ResponseEntity.ok(uMartialart);
	}
}
