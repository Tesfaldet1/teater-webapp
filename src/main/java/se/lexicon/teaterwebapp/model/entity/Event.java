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
    //@CreationTimestamp
   private LocalDateTime startDateTime;
   private LocalDateTime  endDateTime;

   private String description;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "calendar_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "calendar_id")
    )
   private List<Calendar> calendars;

    @OneToMany(
          cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH}
    )
    private List<Member> attendees;

    public Event(String title, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        //this.members = new ArrayList<Member>();
    }
    public void addMember(Member member) {
        attendees.add(member);
    }

    public void removeMember(Member member) {
        attendees.remove(member);
    }

}




