package se.lexicon.teaterwebapp.service;

import se.lexicon.teaterwebapp.model.Dto.ContactInformationDto;

import java.util.Map;

public interface ContactInformationService {
    ContactInformationDto createContactInformation(ContactInformationDto contactInformationDto);

    Map<String, Object> updateContactInformation(int id, ContactInformationDto contactInformationDto);

    ContactInformationDto getContactInformationById(int id);

    void deleteContactInformationById(int id);
}


