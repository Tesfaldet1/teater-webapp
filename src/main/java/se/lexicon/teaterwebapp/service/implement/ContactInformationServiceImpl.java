package se.lexicon.teaterwebapp.service.implement;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;
import se.lexicon.teaterwebapp.model.Dto.ContactInformationDto;
import se.lexicon.teaterwebapp.model.entity.ContactInformation;
import se.lexicon.teaterwebapp.repository.ContactInformationRepository;
import se.lexicon.teaterwebapp.service.ContactInformationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Service
@Transactional
public class ContactInformationServiceImpl implements ContactInformationService {

    private ContactInformationRepository contactInformationRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ContactInformationServiceImpl(ContactInformationRepository contactInformationRepository, ModelMapper modelMapper) {
        this.contactInformationRepository = contactInformationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContactInformationDto createContactInformation(ContactInformationDto contactInformationDto) {
        // Convert ContactInformationDto to ContactInformation entity
        ContactInformation contactInformation = modelMapper.map(contactInformationDto, ContactInformation.class);

        // Save ContactInformation entity
        ContactInformation savedContactInformation = contactInformationRepository.save(contactInformation);

        // Convert saved ContactInformation entity to ContactInformationDto
        return modelMapper.map(savedContactInformation, ContactInformationDto.class);
    }

    @Override
    public Map<String, Object> updateContactInformation(int id, ContactInformationDto contactInformationDto) {
        // Get ContactInformation entity by id

        ContactInformation existingContactInformation = contactInformationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Contact information not found for id: " + id));

        // Update ContactInformation entity with new data from ContactInformationDto
        existingContactInformation.setPhone(contactInformationDto.getPhone());
        existingContactInformation.setEmail(contactInformationDto.getEmail());
        existingContactInformation.setAddress(contactInformationDto.getAddress());
        existingContactInformation.setCity(contactInformationDto.getCity());
        existingContactInformation.setZipcode(contactInformationDto.getZipcode());

        // Save updated ContactInformation entity
        ContactInformation updatedContactInformation = contactInformationRepository.save(existingContactInformation);

        // Convert updated ContactInformation entity to Map<String, Object>
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("id", updatedContactInformation.getId());
        responseMap.put("phone", updatedContactInformation.getPhone());
        responseMap.put("email", updatedContactInformation.getEmail());
        responseMap.put("address", updatedContactInformation.getAddress());
        responseMap.put("city", updatedContactInformation.getCity());
        responseMap.put("zipcode", updatedContactInformation.getZipcode());

        return responseMap;
    }


    @Override
    public ContactInformationDto getContactInformationById(int id) {
        // Get ContactInformation entity by id
        ContactInformation contactInformation = contactInformationRepository.findById(id)

                .orElseThrow(() -> new DataNotFoundException("Contact information not found for id: " + id));

        // Convert ContactInformation entity to ContactInformationDto
        return modelMapper.map(contactInformation, ContactInformationDto.class);
    }

    @Override
    public void deleteContactInformationById(int id) {
        // Delete ContactInformation entity by id
        contactInformationRepository.deleteById(id);
    }
}


