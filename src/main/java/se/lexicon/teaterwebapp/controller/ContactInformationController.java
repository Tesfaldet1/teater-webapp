package se.lexicon.teaterwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.teaterwebapp.model.Dto.ContactInformationDto;
import se.lexicon.teaterwebapp.service.ContactInformationService;

import java.util.Map;

@RestController
@RequestMapping("/contact-information")
public class ContactInformationController {

    private ContactInformationService contactInformationService;

    @Autowired
    public ContactInformationController(ContactInformationService contactInformationService) {
        this.contactInformationService = contactInformationService;
    }
    @PostMapping
    public ResponseEntity<ContactInformationDto> createContactInformation(@RequestBody ContactInformationDto contactInformationDto) {
        ContactInformationDto savedContactInformationDto = contactInformationService.createContactInformation(contactInformationDto);
        return new ResponseEntity<>(savedContactInformationDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInformationDto> getContactInformationById(@PathVariable int id) {
        ContactInformationDto contactInformationDto = contactInformationService.getContactInformationById(id);
        return new ResponseEntity<>(contactInformationDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateContactInformation(@PathVariable int id, @RequestBody ContactInformationDto contactInformationDto) {
        Map<String, Object> updatedContactInformation = contactInformationService.updateContactInformation(id, contactInformationDto);
        return new ResponseEntity<>(updatedContactInformation, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactInformationById(@PathVariable int id) {
        contactInformationService.deleteContactInformationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
