package htwberlin.backend_kasse.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


public class Controller {

    @RestController
    public static class HelloController {

        @GetMapping("/")
        public String index() {
            return "Hello World!";
        }

    }
}
