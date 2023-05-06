package se.lexicon.teaterwebapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Staff  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    @Column(unique = true, nullable = false, length = 30)
    private String email;

    @OneToMany(
           cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
    )
    private List<Calendar> calendarList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private User user;


    public Staff(String firstName, String lastName, LocalDate birthday, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }
}