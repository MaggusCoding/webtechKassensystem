package htwberlin.backend_kasse.controller;

import htwberlin.backend_kasse.entities.KassenbuchungEntity;
import htwberlin.backend_kasse.services.KassenbuchungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class KassenbuchungController {

    @Autowired
    KassenbuchungService kassenbuchungService;


        @PostMapping("/kassenbuchung")
        public KassenbuchungEntity createKassenbuchung(@RequestBody KassenbuchungEntity kassenbuchungEntity){
            return kassenbuchungService.save(kassenbuchungEntity);
        }

        @GetMapping("/kassenbuchung/{id}")
        public KassenbuchungEntity getKassenbuchung(@PathVariable String id){
            int kassenbuchungId = Integer.parseInt(id);
        return kassenbuchungService.get(kassenbuchungId);
        }

        @GetMapping("/kassenbuchung/all")
        public List<KassenbuchungEntity> getAllKassenbuchungen(){
            return kassenbuchungService.findAll();
        }


}
