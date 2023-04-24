package se.lexicon.teaterwebapp.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;
import se.lexicon.teaterwebapp.model.Dto.StaffDto;
import se.lexicon.teaterwebapp.model.entity.Staff;
import se.lexicon.teaterwebapp.repository.StaffRepository;
import se.lexicon.teaterwebapp.service.StaffService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service

public class StaffServiceImpl implements StaffService {
    ModelMapper mapper;
    StaffRepository staffRepository;
    @Autowired
    public StaffServiceImpl(ModelMapper mapper, StaffRepository staffRepository) {
        this.mapper = mapper;
        this.staffRepository = staffRepository;
    }
    @Override

    public StaffDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Person Id should not be null");
        Staff result = staffRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Person not found"));
        return mapper.map(result, StaffDto.class);
    }
    @Override

    public StaffDto findByEmail(String email) throws DataNotFoundException {
        if (email == null) throw new IllegalArgumentException("Email should not be null");
        Staff result = staffRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("Person not found"));
        return mapper.map(result, StaffDto.class);
    }
@Override
    public List<StaffDto> findAll() {
        List<Staff> list = new ArrayList<>();
        staffRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> mapper.map(category, StaffDto.class)).collect(Collectors.toList());
    }
@Override
    @Transactional(rollbackFor = {Exception.class})
    public StaffDto create(StaffDto dto) throws DataDuplicateException, DataNotFoundException {
        if (dto == null) throw new IllegalArgumentException("Person data should not be null");
        if (dto.getId() != null) throw new IllegalArgumentException("id should be null");
        if(staffRepository.findByEmail(dto.getEmail()).isPresent()) throw new DataDuplicateException("Email is duplicate");
        Staff entity = mapper.map(dto, Staff.class);
        Staff result = staffRepository.save(entity);

        return mapper.map(result, StaffDto.class);
    }
    @Override

    @Transactional(rollbackFor = {Exception.class})
    public StaffDto update(StaffDto dto) throws DataNotFoundException {
        System.out.println("dto = " + dto);
        if (dto == null) throw new IllegalArgumentException("Person data should not be null");
        if (dto.getId() == null) throw new IllegalArgumentException("id should not be null");

        findById(dto.getId());

        Staff entity = mapper.map(dto, Staff.class);
        Staff result = staffRepository.save(entity);

        return mapper.map(result, StaffDto.class);
    }
    @Override

    public void delete(Integer id) throws DataNotFoundException {
        findById(id);
        staffRepository.deleteById(id);
    }

}
