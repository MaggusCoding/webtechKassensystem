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
    @CreationTimestamp
    private Instant timestamp;
    @UpdateTimestamp
    private Instant lastUpdatedOn;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "mitarbeiter_id",referencedColumnName = "id")
    private MitarbeiterEntity buchender;

    protected KassenbuchungEntity() {
    }

    public KassenbuchungEntity(BigDecimal buchungsbetrag,MitarbeiterEntity buchender) {
        this.buchungsbetrag = buchungsbetrag;
        this.buchender=buchender;
    }

    public BigDecimal getBuchungsbetrag() {
        return buchungsbetrag;
    }

    public void setBuchungsbetrag(BigDecimal buchungsbetrag) {
        this.buchungsbetrag = buchungsbetrag;
    }

    public int getId() {
        return id;
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

    public MitarbeiterEntity getBuchender() {
        return buchender;
    }

    public void setBuchender(MitarbeiterEntity buchender) {
        this.buchender = buchender;
    }
}
