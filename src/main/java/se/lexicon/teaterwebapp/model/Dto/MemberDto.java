package se.lexicon.teaterwebapp.model.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDto {
    private Integer id;
    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "LastName should not be empty")
    @Size(min = 2, max = 40, message = "LastName length should be between 2-40")
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Size(min = 2, max = 80, message = "Email length should be between 2-80")
    @Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Invalid email format")

    private String email;

    private String street;

    private String city;
}
