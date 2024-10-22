package syksy24.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import syksy24.harjoitustyo.domain.Viesti;
import syksy24.harjoitustyo.domain.ViestiRepository;
import syksy24.harjoitustyo.domain.Henkilo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class RestViestiController {

    @Autowired
    private ViestiRepository viestiRepository;


    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    
    @GetMapping("/viestit")
    public List<Viesti> haeKaikkiViestit() {
        return viestiRepository.findAll(); 
    }

    @GetMapping("/viestit/{id}")
    public Optional<Viesti> haeViestiIdlla(@PathVariable("id") Long id) {
        return viestiRepository.findById(id);
    }

    
    @PostMapping("/viestit")
    public ResponseEntity<Viesti> lisaaViesti(@RequestBody Viesti viesti) {
        viesti.setPaivamaara(LocalDateTime.now());

        // Kirjautuneen käyttäjän haku ja lisäys viestiin
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String kirjautunutKayttaja = auth.getName();
        Henkilo henkilo = userDetailServiceImpl.haeHenkiloKayttajatunnuksella(kirjautunutKayttaja);

        viesti.setHenkilo(henkilo);
        Viesti uusiViesti = viestiRepository.save(viesti);
        ResponseEntity<Viesti> responseEntity = new ResponseEntity<>(uusiViesti, HttpStatus.CREATED);
        return responseEntity;
    }


    @PutMapping("viestit/{id}")
    public Viesti paivitaViesti(@PathVariable("id") Long id, @RequestBody Viesti viesti) {
        Optional<Viesti> olemassaOlevaViesti = viestiRepository.findById(id);

        if (olemassaOlevaViesti.isPresent()) {
            Viesti paivitettavaViesti = olemassaOlevaViesti.get();
            paivitettavaViesti.setTeksti(viesti.getTeksti());
            paivitettavaViesti.setPaivamaara(LocalDateTime.now()); 

            paivitettavaViesti.setHevonen(viesti.getHevonen());
            paivitettavaViesti.setHenkilo(viesti.getHenkilo());
            return viestiRepository.save(paivitettavaViesti);
        } else {
            return viestiRepository.save(viesti);
        }
    }

    @DeleteMapping("viestit/{id}")
    public void poistaViesti(@PathVariable("id") Long id) {
        viestiRepository.deleteById(id);
    }
}

