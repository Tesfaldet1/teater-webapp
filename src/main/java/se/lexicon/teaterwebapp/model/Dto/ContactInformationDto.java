package se.lexicon.teaterwebapp.model.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactInformationDto {
    @NotEmpty
    @Size(min = 4, max = 40)
    private Long id;
    private String address;
    private String phone;
    private String email;
    private UserDto user;
    private String city;
    private String zipcode;
}
