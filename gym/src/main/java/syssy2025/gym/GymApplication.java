package syssy2025.gym;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import syssy2025.gym.domain.AppUser;
import syssy2025.gym.domain.AppUserRepository;
import syssy2025.gym.domain.Coach;
import syssy2025.gym.domain.CoachRepository;
import syssy2025.gym.domain.Customer;
import syssy2025.gym.domain.CustomerRepository;
import syssy2025.gym.domain.MartialArt;
import syssy2025.gym.domain.MartialArtRepository;

@SpringBootApplication
public class GymApplication {
	private static final Logger log = LoggerFactory.getLogger(GymApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GymApplication.class, args);
	}

	@Bean
	public CommandLineRunner gym
		(
			MartialArtRepository maRepository, 
			CoachRepository coaRepository, 
			AppUserRepository uRepository,
			CustomerRepository cuRepository
		) {
		return (args) -> {

			log.info("save a coaches");
			Coach coach1 = new Coach("Pekka", "Pekkanen");
			Coach coach2 = new Coach("Urppo", "McTurbo");

			coaRepository.save(coach1);
			coaRepository.save(coach2);

			log.info("save a couple of lajeja");
			MartialArt art1 = new MartialArt("boxing", coach1);
	
			maRepository.save(art1);

			log.info("save a couple of customers");
			Customer customer1 = new Customer("Onnellinen", "Maksaja", "email@email.com", "polku 123");
			Customer customer2 = new Customer("Surullinen", "Valittaja", "SP@email.com", "katu 123");
			
			cuRepository.save(customer1);
			cuRepository.save(customer2);

			AppUser user1 = new AppUser("Pekka", "Pekkanen", "user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("Urpo", "Urpolainen", "admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);
			
			// log.info("fetch all thingies");
			// for (MartialArt martialart : maRepository.findAll()) {
			// 	log.info(martialart.toString());
			// }
		};
	}

}
