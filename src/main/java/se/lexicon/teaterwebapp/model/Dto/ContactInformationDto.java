package se.lexicon.teaterwebapp.model.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactInformationDto {

    private Long id;
    private String streetAddress;
    private String phone;
    private String city;
    private String zipcode;
}


