package syksy24.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import syksy24.harjoitustyo.domain.Hevonen;
import syksy24.harjoitustyo.domain.HevonenRepository;

import java.util.List;

@RestController
@RequestMapping
public class RestHevosController {

    @Autowired
    private HevonenRepository hevonenRepository;

    @GetMapping("/hevoslistaus")
    public List<Hevonen> getAllHevoset() {
        return hevonenRepository.findAll(); 
    }

}
