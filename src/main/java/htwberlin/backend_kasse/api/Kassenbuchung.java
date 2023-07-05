package htwberlin.backend_kasse.api;

import java.math.BigDecimal;
import java.time.Instant;

public class Kassenbuchung {

    private int id;
    private BigDecimal buchungsbetrag;
    private String comment;
    private Instant timestamp;
    private Instant lastUpdatedOn;
    private int mitarbeiter_id;

    public Kassenbuchung(int id, int mitarbeiter_id, BigDecimal buchungsbetrag, String comment, Instant timestamp, Instant lastUpdatedOn) {
        this.buchungsbetrag = buchungsbetrag;
        this.timestamp = timestamp;
        this.lastUpdatedOn = lastUpdatedOn;
        this.id=id;
        this.mitarbeiter_id = mitarbeiter_id;
        this.comment=comment;
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

    public int getMitarbeiter_id() {
        return mitarbeiter_id;
    }

    public void setMitarbeiter_id(int mitarbeiter_id) {
        this.mitarbeiter_id = mitarbeiter_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
