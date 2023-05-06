package se.lexicon.teaterwebapp.service;

import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;

import se.lexicon.teaterwebapp.model.Dto.CalendarDto;

import java.util.List;

public interface CalendarService {

    CalendarDto findById(Integer id) throws DataNotFoundException;

    List<CalendarDto> findAll();

    CalendarDto create(CalendarDto dto) throws DataDuplicateException;

    CalendarDto update(CalendarDto dto) throws DataNotFoundException;

    void delete(Integer id) throws DataNotFoundException;
}

