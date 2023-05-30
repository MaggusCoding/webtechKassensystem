package htwberlin.backend_kasse.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity(name="kassenbuchung")
public class KassenbuchungEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal buchungsbetrag;
    private String beschreibung;
    @CreationTimestamp
    private Instant timestamp;
    @UpdateTimestamp
    private Instant lastUpdatedOn;


    public KassenbuchungEntity(){};

    public KassenbuchungEntity(BigDecimal buchungsbetrag, String beschreibung){
        this.buchungsbetrag= buchungsbetrag;
        this.beschreibung = beschreibung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBuchungsbetrag() {
        return buchungsbetrag;
    }

    public void setBuchungsbetrag(BigDecimal buchungsbetrag) {
        this.buchungsbetrag = buchungsbetrag;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
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


}
