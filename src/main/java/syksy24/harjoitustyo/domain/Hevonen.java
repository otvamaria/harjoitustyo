package syksy24.harjoitustyo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

//testailua..

@Entity
@Table(name="hevonen")
public class Hevonen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hevosid", nullable = false, updatable = false)
    private long id;

    @NotEmpty(message="Hevosen nimi puuttuu")
    private String nimi;

    private String rotu;

    @NotNull
    private int syntVuosi;

    @ManyToOne
    @JoinColumn(name="henkiloid")
    @JsonBackReference(value="henkilo-hevonen")
    private Henkilo henkilo;

    @OneToMany(mappedBy = "hevonen")
    @JsonManagedReference(value="hevonen-viesti")
    private List<Viesti> viestit;

    public Hevonen() {}

    public Hevonen(String nimi, String rotu, int syntVuosi, Henkilo henkilo, List<Viesti> viestit) {
        this.nimi = nimi;
        this.rotu = rotu;
        this.syntVuosi = syntVuosi;
        this.henkilo = henkilo;
        this.viestit = viestit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getRotu() {
        return rotu;
    }

    public void setRotu(String rotu) {
        this.rotu = rotu;
    }

    public int getSyntVuosi() {
        return syntVuosi;
    }

    public void setSyntVuosi(int syntVuosi) {
        this.syntVuosi = syntVuosi;
    }

    public Henkilo getHenkilo() {
        return henkilo;
    }

    public void setHenkilo(Henkilo henkilo) {
        this.henkilo = henkilo;
    }

    public List<Viesti> getViestit() {
        return viestit;
    }

    public void setViestit(List<Viesti> viestit) {
        this.viestit = viestit;
    }

    @Override
    public String toString() {
        return "Hevonen [id=" + id + ", nimi=" + nimi + ", rotu=" + rotu + ", syntVuosi=" + syntVuosi + ", henkilo="
                + henkilo + ", viestit=" + viestit + "]";
    }

}
