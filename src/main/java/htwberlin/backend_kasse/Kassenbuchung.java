package htwberlin.backend_kasse;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Kassenbuchung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String buchender;
    private BigDecimal buchungsbetrag;
    @CreationTimestamp
    private Instant timestamp;
    @UpdateTimestamp
    private Instant lastUpdatedOn;

    //@OneToOne
   // private Mitarbeiter mitarbeiter;


    public Kassenbuchung(){};

    public Kassenbuchung(String buchender, BigDecimal buchungsbetrag){
        this.buchender=buchender;
        this.buchungsbetrag= buchungsbetrag;
    }

    public int getId(){return id;}

    public void setId(int id){
        this.id=id;
    }

    public String getBuchender(){return buchender;}

    public void setBuchender(String buchender){this.buchender=buchender;};

    public BigDecimal getBuchungsbetrag() {
        return buchungsbetrag;
    }

    public void setBuchungsbetrag(BigDecimal buchungsbetrag) {
        this.buchungsbetrag = buchungsbetrag;
    }


}
