package se.lexicon.teaterwebapp.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;
import se.lexicon.teaterwebapp.model.Dto.CalendarDto;
import se.lexicon.teaterwebapp.model.entity.Calendar;
import se.lexicon.teaterwebapp.repository.CalendarRepository;
import se.lexicon.teaterwebapp.service.CalendarService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarServiceImpl implements CalendarService {

      CalendarRepository calendarRepository;
      ModelMapper modelMapper;
@Autowired
    public CalendarServiceImpl(CalendarRepository calendarRepository, ModelMapper modelMapper) {
        this.calendarRepository = calendarRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CalendarDto findById(Integer id) throws DataNotFoundException {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Calendar not found for id: " + id));
        return modelMapper.map(calendar, CalendarDto.class);
    }



    @Override
    public List<CalendarDto> findAll() {
        List<Calendar> calendars = (List<Calendar>) calendarRepository.findAllByOrderByDateAsc();
        return calendars.stream()
                .map(calendar -> modelMapper.map(calendar, CalendarDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public CalendarDto create(CalendarDto calendarDto) throws DataDuplicateException, DataNotFoundException {
        if (calendarDto == null) throw new IllegalArgumentException("Calendar data should not be null");
        if (calendarDto.getId() != null) throw new IllegalArgumentException("id should be null");
        if (calendarRepository.findByTitle(calendarDto.getTitle()).isPresent()) throw new DataDuplicateException("Title is duplicate");
        Calendar calendar = modelMapper.map(calendarDto, Calendar.class);
        Calendar createdCalendar = calendarRepository.save(calendar);

        return modelMapper.map(createdCalendar, CalendarDto.class);
    }


    @Override
    public CalendarDto update(CalendarDto calendarDto) throws DataNotFoundException {
        Calendar existingCalendar = calendarRepository.findById(calendarDto.getId())
                .orElseThrow(() -> new DataNotFoundException("Calendar not found for id: " + calendarDto.getId()));
        modelMapper.map(calendarDto, existingCalendar);
        Calendar updatedCalendar = calendarRepository.save(existingCalendar);
        return modelMapper.map(updatedCalendar, CalendarDto.class);
    }

    @Override
    public void delete(Integer id) throws DataNotFoundException {
        if (!calendarRepository.existsById(id)) {
            throw new DataNotFoundException("Calendar not found for id: " + id);
        }
        calendarRepository.deleteById(id);
    }
}

