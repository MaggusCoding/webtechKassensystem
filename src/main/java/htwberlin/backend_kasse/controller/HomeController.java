package htwberlin.backend_kasse.controller;


import org.springframework.web.bind.annotation.*;


@RestController
public class HomeController {


        @GetMapping("/api/test/all")
        public String index() {
            return "Hello World, you are not logged in!";
        }


        @GetMapping("/help")
        public String help() {
            return "Example for the help page";
        }

}
