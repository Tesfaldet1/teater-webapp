package se.lexicon.teaterwebapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType name;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Account> accounts;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();






    public Role(RoleType name, List<Account> accounts) {
        this.name = name;
        this.accounts = accounts;
    }


    public Role(RoleType name) {
        this.name = name;
    }

    public Role(List<Account> accounts, Set<User> users) {
        this.accounts = accounts;
        this.users = users;
    }

    public Role(RoleType name, List<Account> accounts, Set<User> users) {
        this.name = name;
        this.accounts = accounts;
        this.users = users;
    }
}

