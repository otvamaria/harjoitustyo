package syksy24.harjoitustyo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import syksy24.harjoitustyo.web.HenkiloController;
import syksy24.harjoitustyo.web.HevonenController;
import syksy24.harjoitustyo.web.ViestiController;

@SpringBootTest
class HarjoitustyoApplicationTests {

    @Autowired
    private ViestiController viestiController;

    @Autowired
    private HenkiloController henkiloController;

    @Autowired
    private HevonenController hevonenController;

    @Test
    void contextLoads() {
    }

    @Test 
    void viestiControllerIsNotNull() {
        assertThat(viestiController).isNotNull();
    }

    @Test 
    void henkiloControllerIsNotNull() {
        assertThat(henkiloController).isNotNull();
    }

    @Test 
    void hevonenControllerIsNotNull() {
        assertThat(hevonenController).isNotNull();
    }
}

