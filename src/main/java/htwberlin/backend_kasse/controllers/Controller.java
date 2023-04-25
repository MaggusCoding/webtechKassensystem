package htwberlin.backend_kasse.controllers;

import htwberlin.backend_kasse.Kassenbuchung;
import htwberlin.backend_kasse.KassenbuchungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    KassenbuchungService kassenbuchungService;

        @PostMapping("/kassenbuchung")
        public Kassenbuchung createKassenbuchung(@RequestBody Kassenbuchung kassenbuchung){
            return kassenbuchungService.save(kassenbuchung);
        }

    @GetMapping("/kassenbuchung")
        public Kassenbuchung getKassenbuchung(@PathVariable String id){
            int kassenbuchungId = Integer.parseInt(id);
        return kassenbuchungService.get(kassenbuchungId);
    }
        @GetMapping("/")
        public String index() {
            return "Hello World!";
        }

        @GetMapping("/help")
        public String help() {
            return "Example for the help page";
        }

}
