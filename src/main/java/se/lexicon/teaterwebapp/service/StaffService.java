package se.lexicon.teaterwebapp.service;

import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;

import se.lexicon.teaterwebapp.model.Dto.StaffDto;

import java.util.List;

public interface StaffService {
    StaffDto findById(Integer id) throws DataNotFoundException;

    StaffDto findByEmail(String email) throws DataNotFoundException;

    List<StaffDto> findAll();

    StaffDto create(StaffDto dto) throws DataDuplicateException, DataNotFoundException;

    StaffDto update(StaffDto dto) throws DataNotFoundException;

    void delete(Integer id) throws DataNotFoundException;


}


