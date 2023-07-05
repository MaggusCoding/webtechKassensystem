package htwberlin.backend_kasse.controller;

import htwberlin.backend_kasse.api.Kassenbuchung;
import htwberlin.backend_kasse.api.KassenbuchungManipulationRequest;
import htwberlin.backend_kasse.api.Mitarbeiter;
import htwberlin.backend_kasse.api.MitarbeiterManipulationRequest;
import htwberlin.backend_kasse.services.KassenbuchungService;
import htwberlin.backend_kasse.services.MitarbeiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class KassenbuchungController {


    @Autowired
    private KassenbuchungService kassenbuchungService;


    @PostMapping("/api/kassenbuchung")
    public ResponseEntity<Void> createKassenbuchung(@RequestBody KassenbuchungManipulationRequest request) throws URISyntaxException {
      var kassenbuchung =   kassenbuchungService.create(request);
      URI uri = new URI("/api/kassenbuchung/"+kassenbuchung.getId());
      return ResponseEntity.created(uri).build();
    }

    @GetMapping("/api/kassenbuchung")
    public ResponseEntity<List<Kassenbuchung>> getKassenbuchung() {
        return ResponseEntity.ok(kassenbuchungService.findAll());
    }
    @GetMapping("/api/kassenbuchung/monthly")
    public ResponseEntity<Map<Date, BigDecimal>> getMonthly() {
        return ResponseEntity.ok(kassenbuchungService.calculateMonthlyRevenue());
    }
    @GetMapping("/api/kassenbuchung/{id}")
    public ResponseEntity<Kassenbuchung> fetchKassenbuchungbyId(@PathVariable Integer id) {
        var kassenbuchung = kassenbuchungService.findById(id);
        return kassenbuchung!=null?ResponseEntity.ok(kassenbuchung):ResponseEntity.notFound().build();
    }
    @PutMapping("/api/kassenbuchung/{id}")
    public ResponseEntity<Kassenbuchung> updateKassenbuchung(@PathVariable Integer id,@RequestBody KassenbuchungManipulationRequest request){
        var kassenbuchung = kassenbuchungService.update(id,request);
        return kassenbuchung!=null?ResponseEntity.ok(kassenbuchung):ResponseEntity.notFound().build();
    }

}
