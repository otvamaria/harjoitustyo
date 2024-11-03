package syksy24.harjoitustyo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import syksy24.harjoitustyo.domain.Viesti;
import syksy24.harjoitustyo.domain.ViestiRepository;
import syksy24.harjoitustyo.domain.Henkilo;
import syksy24.harjoitustyo.domain.HenkiloRepository;
import syksy24.harjoitustyo.domain.Hevonen;
import syksy24.harjoitustyo.domain.HevonenRepository;
import syksy24.harjoitustyo.web.ViestiController;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest(classes = HarjoitustyoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests {

    
    @Autowired
    private ViestiRepository viestiRepository;

    @Autowired
    private HevonenRepository hevonenRepository;

    @Autowired
    private HenkiloRepository henkiloRepository;

    @Autowired
    private ViestiController viestiController;


     //Testejä ViestiController:

    @Test
    public void testAinakinYhdellaHevosellaViesti() {
        List<Viesti> viestit = viestiRepository.findAll();
        Map<Hevonen, List<Viesti>> viestitRyhmitys = viestit.stream()
            .collect(Collectors.groupingBy(Viesti::getHevonen));

        assertThat(viestitRyhmitys).isNotEmpty();
        assertThat(viestitRyhmitys.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void testHevonenLoytyy() {
        Hevonen tessa = hevonenRepository.findByNimi("Tessa");
        assertThat(tessa).isNotNull(); 

        List<Viesti> viestit = viestiRepository.findByHevonen_Nimi(tessa.getNimi());
        assertThat(viestit).isNotEmpty(); 
    }

    @Test
    public void testNaytaViestiLomakePalauttaaOikeanSivun() {
        Model model = new ConcurrentModel();

        String viewName = viestiController.naytaViestiLomake(model);

        assertThat(viewName).isEqualTo("lisaaviesti");
    }

    

    //Testejä HevonenController:

    @Test
    public void testFindAllHevoset() {
        List<Hevonen> hevoset = hevonenRepository.findAll();

        assertThat(hevoset).isNotNull();
        assertThat(hevoset).isNotEmpty(); 
    }

    @Test
    public void testFindSuomenhevonen() {
        List<Hevonen> hevoset = hevonenRepository.findAll();

        assertThat(hevoset).isNotEmpty(); 
        assertThat(hevoset).anyMatch(hevonen -> "Suomenhevonen".equals(hevonen.getRotu()));
    }

    //Testejä HenkiloControllerin toimintaan:

    @Test
    public void testFindAllHenkilot() {
        List<Henkilo> henkilot = henkiloRepository.findAll();

        assertThat(henkilot).isNotNull();
        assertThat(henkilot).isNotEmpty();
    
    }

    @Test
    public void testEtsiHenkiloNimenPerusteella() {
        List<Henkilo> henkilot = henkiloRepository.findAll();

        Optional<Henkilo> haettuHenkilo = henkilot.stream()
            .filter(henkilo -> "Muttonen".equals(henkilo.getSukunimi()))
            .findFirst();

        assertThat(haettuHenkilo).isPresent();
        assertThat(haettuHenkilo.get().getEtunimi()).isEqualTo("Matti");
    }

    

    

       
}


