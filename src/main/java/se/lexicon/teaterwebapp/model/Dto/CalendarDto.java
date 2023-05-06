package se.lexicon.teaterwebapp.model.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import se.lexicon.teaterwebapp.model.entity.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter

public class CalendarDto {
    private Integer id;
    private List<Event> events;
    private String calendarMode;

}


