package se.lexicon.teaterwebapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;

import se.lexicon.teaterwebapp.model.Dto.MemberDto;
import se.lexicon.teaterwebapp.service.MemberService;


import java.util.List;

@RestController
@Validated
@RequestMapping("/api/member")
@CrossOrigin("*")
public class MemberController {
    @Autowired
    MemberService service;
    @GetMapping
    public ResponseEntity<List<MemberDto>> getAll() {
        System.out.println("Get All API has been executed!");
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }
    @PostMapping
    public ResponseEntity<MemberDto> create(@RequestBody @Valid MemberDto dto) throws DataDuplicateException, DataNotFoundException {
        System.out.println("dto = " + dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
    @PutMapping
    public ResponseEntity<MemberDto> update(@RequestBody @Valid MemberDto dto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.update(dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws DataNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
