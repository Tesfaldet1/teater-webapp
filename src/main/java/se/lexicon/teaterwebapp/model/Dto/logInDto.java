package se.lexicon.teaterwebapp.model.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import se.lexicon.teaterwebapp.model.entity.User;

@Getter
@Setter
public class logInDto {
    @NotEmpty
    @Size(min = 4, max = 40)
    private String userName;

    @Size(min = 4, max = 40)
    private String password;
    private User user;
}
