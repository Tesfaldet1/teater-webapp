package se.lexicon.teaterwebapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data // Getter and Setter, EqualsAndHashCode and RequiredArgConstructor
@Entity

public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "calendar_events",
            joinColumns = @JoinColumn(name = "calendar_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;
    private String calendarMode;

    public Calendar(List<Event> events, String calendarMode) {
        this.events = events;
        this.calendarMode = calendarMode;
    }
    public void addEvent(Event event) {
        events.add(event);
    }
    public void removeEvent(Event event) {
        events.remove(event);
    }
}
