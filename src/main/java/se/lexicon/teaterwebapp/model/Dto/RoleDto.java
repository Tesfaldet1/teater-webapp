package se.lexicon.teaterwebapp.model.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter

public class RoleDto {
    private int id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "name length should be between 3 and 30")
    private String name;
}