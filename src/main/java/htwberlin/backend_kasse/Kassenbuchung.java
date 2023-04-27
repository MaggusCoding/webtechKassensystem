package htwberlin.backend_kasse;


import jakarta.persistence.*;

@Entity
public class Kassenbuchung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String buchender;


    public Kassenbuchung(){};

    public Kassenbuchung(String buchender){
        this.buchender=buchender;
    }

    public int getId(){return id;}

    public void setId(int id){
        this.id=id;
    }

    public String getBuchender(){return buchender;}

    public void setBuchender(String buchender){this.buchender=buchender;};

}
