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

import java.util.Date;


@Entity
@Table(name="viesti")
public class Viesti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="viesti_id", nullable = false, updatable = false)
    private long id;

    @NotEmpty
    private String teksti;

    @NotNull
    private Date paivamaara;

    @ManyToOne
    @JoinColumn(name="hevosid")
    @JsonBackReference(value="hevonen-viesti")
    private Hevonen hevonen;

    @ManyToOne
    @JoinColumn(name="henkiloid")
    @JsonBackReference(value="henkilo-viesti")
    private Henkilo henkilo;

    public Viesti() {}

    public Viesti(String teksti, Date paivamaara, Hevonen hevonen, Henkilo henkilo) {
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

    public Date getPaivamaara() {
        return paivamaara;
    }

    public void setPaivamaara(Date paivamaara) {
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
