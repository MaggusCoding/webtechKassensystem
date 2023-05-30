package htwberlin.backend_kasse.api;

import java.math.BigDecimal;
import java.time.Instant;

public class Kassenbuchung {

    private int id;
    private BigDecimal buchungsbetrag;

    private Instant timestamp;
    private Instant lastUpdatedOn;

    public Kassenbuchung(int id, BigDecimal buchungsbetrag, Instant timestamp, Instant lastUpdatedOn) {
        this.buchungsbetrag = buchungsbetrag;
        this.timestamp = timestamp;
        this.lastUpdatedOn = lastUpdatedOn;
        this.id=id;
    }


    //Getters Setters

    public int getId() {
        return id;
    }


    public BigDecimal getBuchungsbetrag() {
        return buchungsbetrag;
    }

    public void setBuchungsbetrag(BigDecimal buchungsbetrag) {
        this.buchungsbetrag = buchungsbetrag;
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
