package syksy24.harjoitustyo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import syksy24.harjoitustyo.domain.ViestiRepository;
import syksy24.harjoitustyo.domain.HevonenRepository;
import syksy24.harjoitustyo.domain.HenkiloRepository;
import syksy24.harjoitustyo.domain.Henkilo;
import syksy24.harjoitustyo.domain.Hevonen;
import syksy24.harjoitustyo.domain.Viesti;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class HarjoitustyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}

	@Bean
	public CommandLineRunner testiData(ViestiRepository viestiRepository, HevonenRepository hevonenRepository, HenkiloRepository henkiloRepository) {
		return (args) -> {

			Henkilo henkilo1 = new Henkilo("user", "$2a$10$aYzIugkyZ/U18AMCCSbpSuptM096SGxTWYX5YqzOUqXfU8GXOxOnK", "Liisa", "Lopponen", "040-98765432", "liisa.lopponen@osoite.com", "ROLE_USER", null, null);
			Henkilo henkilo2 = new Henkilo("admin", "$2a$10$0biol8FjOcs3mBRTfRgNqOauNXFPWNuOacp9oAPNhImMHUtVgteOW", "Maija", "Miikkulainen", "050-4561237", "maija.m@sahkoposti.com", "ROLE_ADMIN", null, null);
			Henkilo henkilo3 = new Henkilo("user2", "$2a$10$dMksj5T2YXRSnIlKM/uoauHyrrMdAC4G5pqxOWKkfkYbH52asLmcu", "Matti", "Muttonen", "040-1234667", "mattimuttonen@mailiosoite.fi", "ROLE_USER", null, null);

			henkiloRepository.save(henkilo1);
			henkiloRepository.save(henkilo2);
			henkiloRepository.save(henkilo3);

			Hevonen hevonen1 = new Hevonen("Polle", "Suomenhevonen", 2015, henkilo2, null);
			Hevonen hevonen2 = new Hevonen("Tessa", "Puoliverinen", 2013, henkilo3, null);
			Hevonen hevonen3 = new Hevonen("Nero", "Haflinger", 2020, henkilo2, null);
			Hevonen hevonen4 = new Hevonen("Donna", "Connemara", 2016, henkilo1, null);

			hevonenRepository.save(hevonen1);
			hevonenRepository.save(hevonen2);
			hevonenRepository.save(hevonen3);
			hevonenRepository.save(hevonen4);

			Viesti viesti1 = new Viesti("Muista laittaa Pollelle loimi päälle yöksi", LocalDateTime.now(), hevonen1, henkilo2);
			Viesti viesti2 = new Viesti("Nerolta kenkä irronnut, kengittäjä tilattu", LocalDateTime.now(), hevonen3, henkilo2);
			Viesti viesti3 = new Viesti("Tessan etujalassa haava, puhdistus ohjeen mukaan päivittäin", LocalDateTime.now(), hevonen2, henkilo1);
			Viesti viesti4 = new Viesti("Neron kaura-annos puolitettu toistaiseksi", LocalDateTime.now(), hevonen3, henkilo3);
			Viesti viesti5 = new Viesti("Muista puhdistaa Donnan tarha kunnolla", LocalDateTime.now(), hevonen4, henkilo1);

			viestiRepository.save(viesti1);
			viestiRepository.save(viesti2);
			viestiRepository.save(viesti3);
			viestiRepository.save(viesti4);
			viestiRepository.save(viesti5);

			hevonen1.setViestit(Arrays.asList(viesti1)); 
        	hevonen2.setViestit(Arrays.asList(viesti3)); 
        	hevonen3.setViestit(Arrays.asList(viesti2, viesti4));
			hevonen4.setViestit(Arrays.asList(viesti5));

			hevonenRepository.save(hevonen1);
        	hevonenRepository.save(hevonen2);
        	hevonenRepository.save(hevonen3);
			hevonenRepository.save(hevonen4);

			henkilo2.setHevoset(Arrays.asList(hevonen1, hevonen3)); 
        	henkilo3.setHevoset(Arrays.asList(hevonen2));
			henkilo1.setHevoset(Arrays.asList(hevonen4));

			henkilo2.setViestit(Arrays.asList(viesti1, viesti2));
        	henkilo1.setViestit(Arrays.asList(viesti3, viesti5)); 
        	henkilo3.setViestit(Arrays.asList(viesti4)); 

			henkiloRepository.save(henkilo1);
        	henkiloRepository.save(henkilo2);
        	henkiloRepository.save(henkilo3);
			
			
		};



	}

}

