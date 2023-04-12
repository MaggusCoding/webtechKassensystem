package htwberlin.backend_kasse.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Controller {

        @GetMapping("/")
        public String index() {
            return "Hello World!";
        }

        @GetMapping("/help")
        public String help() {
            return "Example for the help page";
        }

}
