package syksy24.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import syksy24.harjoitustyo.domain.Viesti;
import syksy24.harjoitustyo.domain.ViestiRepository;
import syksy24.harjoitustyo.exception.ViestiNotFoundException;
import syksy24.harjoitustyo.domain.Henkilo;

import java.time.LocalDateTime;
import java.util.List;

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
    public Viesti haeViestiIdlla(@PathVariable("id") Long id) {
        return viestiRepository.findById(id)
            .orElseThrow(() -> new ViestiNotFoundException("Viestiä ei löytynyt ID:llä: " + id));
    }

    
    @PostMapping("/viestit")
    public ResponseEntity<Viesti> lisaaViesti(@Valid @RequestBody Viesti viesti) {
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
    public ResponseEntity<Viesti> paivitaViesti(@Valid @PathVariable("id") Long id, @RequestBody Viesti viesti) {
        Viesti paivitettavaViesti = viestiRepository.findById(id)
            .orElseThrow(() -> new ViestiNotFoundException("Viestiä ei löytynyt ID:llä: " + id));
    
        paivitettavaViesti.setTeksti(viesti.getTeksti());
        paivitettavaViesti.setPaivamaara(LocalDateTime.now()); 
        paivitettavaViesti.setHevonen(viesti.getHevonen());
        paivitettavaViesti.setHenkilo(viesti.getHenkilo());
    
        return ResponseEntity.ok(viestiRepository.save(paivitettavaViesti));
    }

    @DeleteMapping("viestit/{id}")
    public ResponseEntity<Void> poistaViesti(@PathVariable("id") Long id) {
   
        viestiRepository.deleteById(id);
    
        // Palauttaa 204 koodin
        return ResponseEntity.noContent().build();
}
}

