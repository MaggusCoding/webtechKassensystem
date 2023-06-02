package htwberlin.backend_kasse.entities;

import htwberlin.backend_kasse.api.Kassenbuchung;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(name="mitarbeiter")
public class MitarbeiterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String vorname;
    private String nachname;
    private String studiengang;
    @CreationTimestamp
    private Instant timestamp;
    @UpdateTimestamp
    private Instant lastUpdatedOn;
    @OneToMany(mappedBy = "buchender",fetch = FetchType.EAGER)
    private List<KassenbuchungEntity> buchungen= new ArrayList<>();


    protected MitarbeiterEntity() {
    }

    public MitarbeiterEntity(String vorname, String nachname, String studiengang) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.studiengang = studiengang;
    }

    public int getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Instant getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Instant lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public List<KassenbuchungEntity> getBuchungen() {
        return buchungen;
    }

    public void setBuchungen(List<KassenbuchungEntity> buchungen) {
        this.buchungen = buchungen;
    }
}
