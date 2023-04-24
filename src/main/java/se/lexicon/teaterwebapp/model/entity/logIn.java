package se.lexicon.teaterwebapp.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class logIn {
    private String userName;
    private String password;
    private User user;
}
