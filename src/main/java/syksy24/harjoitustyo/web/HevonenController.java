package syksy24.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import syksy24.harjoitustyo.domain.HevonenRepository;

@Controller
public class HevonenController {

    @Autowired
    private HevonenRepository hevonenRepository;

    @GetMapping("/hevoset")
    public String listaaHevoset(Model model) {
        model.addAttribute("hevoset", hevonenRepository.findAll());
        return "hevoset";
    }
}
