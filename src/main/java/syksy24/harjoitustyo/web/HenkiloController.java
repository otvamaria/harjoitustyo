package syksy24.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import syksy24.harjoitustyo.domain.HenkiloRepository;

@Controller
public class HenkiloController {

    @Autowired
    private HenkiloRepository henkiloRepository;


    @GetMapping("/henkilot")
    public String naytaHenkilot(Model model) {
        model.addAttribute("henkilot", henkiloRepository.findAll());
        return "henkilot"; 
    }
}
