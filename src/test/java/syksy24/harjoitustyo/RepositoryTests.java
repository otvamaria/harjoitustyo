package syksy24.harjoitustyo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import syksy24.harjoitustyo.domain.Viesti;
import syksy24.harjoitustyo.domain.ViestiRepository;
import syksy24.harjoitustyo.domain.Henkilo;
import syksy24.harjoitustyo.domain.Hevonen;
import syksy24.harjoitustyo.domain.HevonenRepository;
import syksy24.harjoitustyo.web.UserDetailServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(classes = HarjoitustyoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests {

    
    @Autowired
    private ViestiRepository viestiRepository;

    @Autowired
    private HevonenRepository hevonenRepository;

    @Autowired 
    private UserDetailServiceImpl userDetailServiceImpl;

    @Test
    public void ainakinYhdellaHevosellaViesti() {
        List<Viesti> viestit = viestiRepository.findAll();
        Map<Hevonen, List<Viesti>> viestitRyhmitys = viestit.stream()
            .collect(Collectors.groupingBy(Viesti::getHevonen));

        assertThat(viestitRyhmitys).isNotEmpty();
        assertThat(viestitRyhmitys.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void hevonenLoytyy() {
        Hevonen tessa = hevonenRepository.findByNimi("Tessa");
        assertThat(tessa).isNotNull(); 

        List<Viesti> viestit = viestiRepository.findByHevonen_Nimi(tessa.getNimi());
        assertThat(viestit).isNotEmpty(); 
    }

       
}


