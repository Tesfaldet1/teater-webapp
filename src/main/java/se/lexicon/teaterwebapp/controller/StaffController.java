package se.lexicon.teaterwebapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;


import se.lexicon.teaterwebapp.model.Dto.StaffDto;
import se.lexicon.teaterwebapp.model.entity.Staff;
import se.lexicon.teaterwebapp.service.StaffService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/staff")
@CrossOrigin("*")
public class StaffController {
    @Autowired
    StaffService service;

    @GetMapping
    public ResponseEntity<List<StaffDto>> getAll() {
        System.out.println("Get All API has been executed!");
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<StaffDto> create(@RequestBody @Valid StaffDto dto) throws DataDuplicateException, DataNotFoundException {
        System.out.println("dto = " + dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping
    public ResponseEntity<StaffDto> update(@RequestBody @Valid StaffDto dto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws DataNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}