package se.lexicon.teaterwebapp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique = true, nullable = false, length = 80)
    private String username;
    @Column(nullable = false, length = 80)
    private String password;
    private boolean expired;
    @Column(unique = true, nullable = false, length = 30)
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(
            mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH}
    )
    private List<ContactInformation>contactInformationList;

    @OneToMany(
            mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,


            CascadeType.REFRESH}
    )
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String email) {
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(boolean expired, String username) {
        this.expired = expired;
        this.email = username;
    }
    public  void addRole(Role role){
        if (roles.contains(role)) {
            throw new DataDuplicateException("the role already exist");
        }
        roles.add(role);
    }

    public  void removeRole(Role role){
        if(!roles.contains(role)){
            throw  new DataNotFoundException("the role data was null");
        }
        roles.remove(role);
    }
}

