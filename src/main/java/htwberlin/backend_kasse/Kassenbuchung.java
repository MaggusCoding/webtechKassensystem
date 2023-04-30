package htwberlin.backend_kasse;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Kassenbuchung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String buchender;

    private BigDecimal buchungsbetrag;


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
