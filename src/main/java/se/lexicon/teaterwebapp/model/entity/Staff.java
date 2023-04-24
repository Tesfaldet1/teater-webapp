package se.lexicon.teaterwebapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.teaterwebapp.model.Dto.StaffDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    @Column(unique = true, nullable = false, length = 30)
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private User user;
    @OneToMany(
            mappedBy = "staff", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH}
    )
    private List<ContactInformation> contactInformationList;
    public Staff(StaffDto createdStaffDto) {
        this.firstName = createdStaffDto.getFirstName();
        this.lastName = createdStaffDto.getLastName();
        this.email= createdStaffDto.getEmail();

    }

    public Staff(String firstName, String lastName, LocalDate parse, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Staff(String firstName, String lastName, Date birthday, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }
}