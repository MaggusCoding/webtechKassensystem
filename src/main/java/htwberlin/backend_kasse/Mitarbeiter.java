package htwberlin.backend_kasse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
    public class Mitarbeiter {
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


        public Mitarbeiter(){};

        public Mitarbeiter(String vorname,String nachname, String studiengang){
            this.vorname = vorname;
            this.nachname = nachname;
            this.studiengang = studiengang;
        }
}
