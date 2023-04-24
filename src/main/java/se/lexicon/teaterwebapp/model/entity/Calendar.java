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

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime endTime;


    @OneToMany(
            mappedBy = "calendar", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH}
    )
    private List<Event> events;


}
