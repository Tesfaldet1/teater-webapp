package se.lexicon.teaterwebapp.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String email;
    @Column(nullable = false)
    private String password;
    //private boolean expired;
  //  private boolean locked;

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
