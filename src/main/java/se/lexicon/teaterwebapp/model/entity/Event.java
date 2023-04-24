package se.lexicon.teaterwebapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data // Getter and Setter, EqualsAndHashCode and RequiredArgConstructor

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @CreationTimestamp
   private LocalDateTime startDateTime;
   private LocalDateTime  endDateTime;
   private String organizer;
   private String description;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
   private Calendar calendar;

    @OneToMany(
            mappedBy = "event", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH}
    )
    private List<Member> members = new ArrayList<>();

    public Event(String title, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        //this.members = new ArrayList<Member>();
    }
    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }
}




