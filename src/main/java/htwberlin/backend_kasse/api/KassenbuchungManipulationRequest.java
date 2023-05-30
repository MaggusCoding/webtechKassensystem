package htwberlin.backend_kasse.api;

import java.math.BigDecimal;
import java.time.Instant;

public class KassenbuchungManipulationRequest {

    private BigDecimal buchungsbetrag;

    private Instant timestamp;
    private Instant lastUpdatedOn;

    public KassenbuchungManipulationRequest(BigDecimal buchungsbetrag, Instant timestamp, Instant lastUpdatedOn) {
        this.buchungsbetrag = buchungsbetrag;
        this.timestamp = timestamp;
        this.lastUpdatedOn = lastUpdatedOn;
    }


    //Getters Setters


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
