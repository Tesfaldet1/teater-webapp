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
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    private boolean expired;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToMany(
            mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH}
    )
    private List<ContactInformation>contactInformationList;

    public User(Account account, Set<Role> roles, List<ContactInformation> contactInformationList) {
        this.account = account;
        this.roles = roles;
        this.contactInformationList = contactInformationList;
    }

    public User(Account account, Set<Role> roles) {
        this.account = account;
        this.roles = roles;
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

