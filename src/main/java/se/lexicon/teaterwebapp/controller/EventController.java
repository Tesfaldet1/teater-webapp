package se.lexicon.teaterwebapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @GetMapping("/myEvent")
    public String EventClassImplementation(){
        return "Welcome to the event class";
    }
}
