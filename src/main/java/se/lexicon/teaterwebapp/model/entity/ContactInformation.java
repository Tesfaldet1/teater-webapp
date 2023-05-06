package se.lexicon.teaterwebapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phone;
    private String streetAddress;
    private String city;
    private String zipcode;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private User user;

    public ContactInformation(String phone, String streetAddress, String city) {
        this.phone = phone;
        this.streetAddress = streetAddress;
        this.city = city;
    }
}
