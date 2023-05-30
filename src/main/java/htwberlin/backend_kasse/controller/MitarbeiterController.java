package htwberlin.backend_kasse.controller;

import htwberlin.backend_kasse.api.Mitarbeiter;
import htwberlin.backend_kasse.api.MitarbeiterCreateRequest;
import htwberlin.backend_kasse.services.MitarbeiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class MitarbeiterController {


    @Autowired
    private MitarbeiterService mitarbeiterService;


    @PostMapping("/api/mitarbeiter")
    public ResponseEntity<Void> createMitarbeiter(@RequestBody MitarbeiterCreateRequest request) throws URISyntaxException {
      var mitarbeiter =   mitarbeiterService.create(request);
      URI uri = new URI("/api/mitarbeiter/"+mitarbeiter.getId());
      return ResponseEntity.created(uri).build();
    }

    @GetMapping("/api/mitarbeiter")
    public ResponseEntity<List<Mitarbeiter>> getMitarbeiter() {
        return ResponseEntity.ok(mitarbeiterService.findAll());
    }


}
