package syksy24.harjoitustyo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name="henkilo")
public class Henkilo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "henkiloid", nullable = false, updatable = false)
    private long id;

    @NotEmpty(message="Etunimi puuttuu")
    private String etunimi;

    @NotEmpty(message="Sukunimi puuttuu")
    private String sukunimi;

    @NotEmpty(message="Puhelinnumero on pakollinen tieto")
    private String puhelin;

    @Email(message="Tarkista sähköpostiosoite")
    private String sahkoposti;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "henkilo")
    @JsonManagedReference(value="henkilo-hevonen")
    private List<Hevonen> hevoset;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "henkilo")
    @JsonManagedReference(value="henkilo-viesti")
    private List<Viesti> viestit;

    public Henkilo() {}

    public Henkilo(String etunimi, String sukunimi, String puhelin, String sahkoposti, List<Hevonen> hevoset, List<Viesti> viestit) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.puhelin = puhelin;
        this.sahkoposti = sahkoposti;
        this.hevoset = hevoset;
        this.viestit = viestit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getPuhelin() {
        return puhelin;
    }

    public void setPuhelin(String puhelin) {
        this.puhelin = puhelin;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public List<Hevonen> getHevoset() {
        return hevoset;
    }

    public void setHevoset(List<Hevonen> hevoset) {
        this.hevoset = hevoset;
    }

    public List<Viesti> getViestit() {
        return viestit;
    }

    public void setViestit(List<Viesti> viestit) {
        this.viestit = viestit;
    }

    


}
