package se.lexicon.teaterwebapp.service;

import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;

import se.lexicon.teaterwebapp.model.Dto.EventDto;

import java.util.List;

public interface EventService {
    EventDto findById(Long id) throws DataNotFoundException;

    List<EventDto> findAll();

    EventDto create(EventDto dto) throws DataDuplicateException, DataNotFoundException;

    EventDto update(EventDto dto) throws DataNotFoundException;

    void delete(Long id) throws DataNotFoundException;
}

