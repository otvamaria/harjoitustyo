package syksy24.harjoitustyo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import syksy24.harjoitustyo.domain.Viesti;
import syksy24.harjoitustyo.domain.ViestiRepository;
import syksy24.harjoitustyo.domain.Henkilo;
import syksy24.harjoitustyo.domain.HenkiloRepository;
import syksy24.harjoitustyo.domain.Hevonen;
import syksy24.harjoitustyo.domain.HevonenRepository;


@Controller
public class ViestiController {

    @Autowired
    private ViestiRepository viestiRepository;

    @Autowired
    private HevonenRepository hevonenRepository;

    @Autowired
    private HenkiloRepository henkiloRepository;

    @Autowired 
    private UserDetailServiceImpl userDetailServiceImpl;

    @RequestMapping("/index")
    @ResponseBody
    public String returnMessage() {
        return "Tervetuloa tallin viestitaululle";
    }

    
    @GetMapping("/viestit")
    public String naytaViestit(Model model) {
        List<Viesti> viestit = viestiRepository.findAll();
    
        Map<Hevonen, List<Viesti>> viestitRyhmitys = viestit.stream()
        .collect(Collectors.groupingBy(Viesti::getHevonen));

        model.addAttribute("viestitRyhmitys", viestitRyhmitys);
        return "viestit";
}


    @GetMapping("/lisaaviesti")
    public String naytaViestiLomake(Model model) {

        model.addAttribute("viesti", new Viesti());
        model.addAttribute("hevoset", hevonenRepository.findAll());
    
    return "lisaaviesti";
}


    @PostMapping("/tallennaviesti")
    public String tallennaViesti(@ModelAttribute Viesti viesti) {
        viesti.setPaivamaara(LocalDateTime.now());

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String kirjautunutKayttaja = auth.getName(); 
        Henkilo henkilo = userDetailServiceImpl.haeHenkiloKayttajatunnuksella(kirjautunutKayttaja);

        viesti.setHenkilo(henkilo);
        viestiRepository.save(viesti);

        return "redirect:/viestit";
}

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("poista/{id}")
    public String poistaViesti(@PathVariable("id") Long id) {
        viestiRepository.deleteById(id);
        return "redirect:/viestit";
    }

    @GetMapping("muokkaa/{id}")
    public String muokkaaViesti(@PathVariable("id") Long id, Model model) {
        model.addAttribute("muokkaaviestia", viestiRepository.findById(id).orElse(null));
        model.addAttribute("horses", hevonenRepository.findAll());
        model.addAttribute("persons", henkiloRepository.findAll());
        return "muokkaaviestia";
    }

    @PostMapping("/tallennamuokkaukset")
    public String tallennaMuokattuViesti(@Valid @ModelAttribute("muokkaaviestia") Viesti viesti, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("hevoset", hevonenRepository.findAll());
            model.addAttribute("henkilot", henkiloRepository.findAll());
            return "muokkaaviestia";
        }
        viestiRepository.save(viesti);
        return "redirect:/viestit";
    }
}
