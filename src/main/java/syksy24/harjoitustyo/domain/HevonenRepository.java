package syksy24.harjoitustyo.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HevonenRepository extends JpaRepository<Hevonen, Long> {

    Hevonen findByNimi(String nimi);
    List<Hevonen> findAllByOrderByNimiAsc();
}
