package syksy24.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import syksy24.harjoitustyo.domain.Henkilo;
import syksy24.harjoitustyo.domain.HenkiloRepository;

import java.util.List;

@RestController
@RequestMapping
public class RestHenkiloController {

    @Autowired
    private HenkiloRepository henkiloRepository;

    @GetMapping("/henkilolistaus")
    public List<Henkilo> getAllHenkilot() {
        return henkiloRepository.findAll(); 
    }

}
