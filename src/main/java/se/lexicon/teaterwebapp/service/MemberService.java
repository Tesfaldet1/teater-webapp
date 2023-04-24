package se.lexicon.teaterwebapp.service;

import org.springframework.stereotype.Service;
import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;
import se.lexicon.teaterwebapp.model.Dto.MemberDto;


import java.util.List;

public interface MemberService {
    MemberDto findById(Integer id) throws DataNotFoundException;

    MemberDto findByEmail(String email) throws DataNotFoundException;

    List< MemberDto> findAll();

    MemberDto create( MemberDto dto) throws DataDuplicateException, DataNotFoundException;

    MemberDto update( MemberDto dto) throws DataNotFoundException;

    void delete(Integer id) throws DataNotFoundException;


}
