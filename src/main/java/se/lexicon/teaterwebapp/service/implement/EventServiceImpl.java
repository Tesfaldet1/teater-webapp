package se.lexicon.teaterwebapp.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;
import se.lexicon.teaterwebapp.model.Dto.EventDto;
import se.lexicon.teaterwebapp.model.entity.Event;
import se.lexicon.teaterwebapp.repository.EventRepository;
import se.lexicon.teaterwebapp.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EventDto findById(Long id) throws DataNotFoundException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Event not found with id " + id));
        return modelMapper.map(event, EventDto.class);
    }

    @Override
    public List<EventDto> findAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> modelMapper.map(event, EventDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EventDto create(EventDto eventDto) throws DataNotFoundException {
        Event event = modelMapper.map(eventDto, Event.class);
        Event createdEvent = eventRepository.save(event);
        return modelMapper.map(createdEvent, EventDto.class);
    }

    @Override
    public EventDto update(EventDto eventDto) throws DataNotFoundException {
        if (eventDto.getId() == null) throw new IllegalArgumentException("id should not be null");
        Event event = modelMapper.map(eventDto, Event.class);
        Event updatedEvent = eventRepository.save(event);
        return modelMapper.map(updatedEvent, EventDto.class);
    }

    @Override
    public void delete(Long id) throws DataNotFoundException {
        if (!eventRepository.existsById(id)) throw new DataNotFoundException("Event not found with id " + id);
        eventRepository.deleteById(id);
    }
}
