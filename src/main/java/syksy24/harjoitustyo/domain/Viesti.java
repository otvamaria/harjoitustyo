package syksy24.harjoitustyo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
@Table(name="viesti")
public class Viesti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="viesti_id", nullable = false, updatable = false)
    private long id;

    @NotEmpty(message="Viestikenttä ei voi olla tyhjä")
    @Column(name="teksti")
    @Size(min = 1, max = 100, message="Viestin pituuden täytyy olla 1-100 merkkiä.")
    private String teksti;

    @NotNull(message="Päivämäärä on pakollinen tieto.")
    @Column(name="paivamaara")
    private LocalDateTime paivamaara;

    @ManyToOne
    @JoinColumn(name="hevosid")
    @JsonBackReference(value="hevonen-viesti")
    private Hevonen hevonen;

    @ManyToOne
    @JoinColumn(name="henkiloid")
    @JsonBackReference(value="henkilo-viesti")
    private Henkilo henkilo;

    public Viesti() {}

    public Viesti(String teksti, LocalDateTime paivamaara, Hevonen hevonen, Henkilo henkilo) {
        this.teksti = teksti;
        this.paivamaara = paivamaara;
        this.hevonen = hevonen;
        this.henkilo = henkilo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeksti() {
        return teksti;
    }

    public void setTeksti(String teksti) {
        this.teksti = teksti;
    }

    public LocalDateTime getPaivamaara() {
        return paivamaara;
    }

    public void setPaivamaara(LocalDateTime paivamaara) {
        this.paivamaara = paivamaara;
    }

    public Hevonen getHevonen() {
        return hevonen;
    }

    public void setHevonen(Hevonen hevonen) {
        this.hevonen = hevonen;
    }

    public Henkilo getHenkilo() {
        return henkilo;
    }

    public void setHenkilo(Henkilo henkilo) {
        this.henkilo = henkilo;
    }

    

}
