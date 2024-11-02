package syksy24.harjoitustyo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ViestiRepository extends JpaRepository<Viesti, Long> {

        List<Viesti> findByHevonen_Nimi(String nimi); 
        Viesti findByTeksti(String teksti);
    }
    