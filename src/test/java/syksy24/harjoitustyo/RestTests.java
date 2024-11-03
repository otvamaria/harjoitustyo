package syksy24.harjoitustyo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import syksy24.harjoitustyo.domain.Viesti;
import syksy24.harjoitustyo.domain.ViestiRepository;



@SpringBootTest
public class RestTests {

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @Autowired
    private ViestiRepository viestiRepository;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void testHaeKaikkiViestitStatusOk() throws Exception {
        mockMvc.perform(get("/viestit"))
                .andExpect(status().isOk()); 
    }

    @Test
    public void testHaeKaikkiViestitResponseTypeApplicationJson() throws Exception {
        mockMvc.perform(get("/viestit"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isOk()); 
    }

    @Test
    public void testHaeViestiIdllaExists() throws Exception {
        int existingId = 51; 

        mockMvc.perform(get("/viestit/{id}", existingId))
            .andExpect(status().isOk()) 
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)) 
            .andExpect(jsonPath("$.teksti").value("Muista laittaa Pollelle loimi ulos")); 
}
    

   
    }

    // Voit lisätä lisää testejä viestien tarkastamiseksi...



