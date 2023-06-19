package se.lexicon.teaterwebapp.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.lexicon.teaterwebapp.model.Dto.CalendarDto;
import se.lexicon.teaterwebapp.service.CalendarService;

import java.util.List;

@RestController
@RequestMapping("/api/calendars")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalendarDto> findById(@PathVariable Integer id) {
        CalendarDto calendarDto = calendarService.findById(id);
        return ResponseEntity.ok(calendarDto);
    }

    @PostMapping
    public ResponseEntity<CalendarDto> create(@Valid @RequestBody CalendarDto calendarDto) {
        CalendarDto createdCalendarDto = calendarService.create(calendarDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCalendarDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarDto> update(@PathVariable Integer id, @Valid @RequestBody CalendarDto calendarDto) {
        calendarDto.setId(id);
        CalendarDto updatedCalendarDto = calendarService.update(calendarDto);
        return ResponseEntity.ok(updatedCalendarDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        calendarService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CalendarDto>> findAll() {
        List<CalendarDto> calendarDtos = calendarService.findAll();
        return ResponseEntity.ok(calendarDtos);
    }

}

