package se.lexicon.teaterwebapp.model.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

public class CalendarDto {
    @NotNull(message = "Event id is required")
    private Integer id;
    @NotBlank(message = "Event name is required")
    private String title;
    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime endTime;


}
