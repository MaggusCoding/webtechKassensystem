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
    private String comment;
    @CreationTimestamp
    private Instant timestamp;
    @UpdateTimestamp
    private Instant lastUpdatedOn;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "mitarbeiter_id",referencedColumnName = "id")
    private MitarbeiterEntity buchender;

    public KassenbuchungEntity() {
    }

    public KassenbuchungEntity(BigDecimal buchungsbetrag,MitarbeiterEntity buchender, String comment) {
        this.buchungsbetrag = buchungsbetrag;
        this.buchender=buchender;
        this.comment=comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }
}
