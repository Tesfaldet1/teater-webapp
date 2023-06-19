package se.lexicon.teaterwebapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;
import se.lexicon.teaterwebapp.model.Dto.EventDto;
import se.lexicon.teaterwebapp.model.Dto.UserDto;
import se.lexicon.teaterwebapp.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {


    @Autowired
    private EventService eventService;


    @GetMapping
    public ResponseEntity<List<EventDto>> getAll() {
        System.out.println("Get All API has been executed!");
        return ResponseEntity.status(HttpStatus.OK).body(eventService.findAll());
    }

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable Long id) {
        return eventService.findById(id);

    }
    @PostMapping
    public ResponseEntity<EventDto> create(@RequestBody @Valid EventDto dto) throws DataDuplicateException, DataNotFoundException {
        System.out.println("dto = " + dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(dto));
    }
    @PutMapping("/{id}")
    public EventDto updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        return eventService.update(eventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);

    }
}


