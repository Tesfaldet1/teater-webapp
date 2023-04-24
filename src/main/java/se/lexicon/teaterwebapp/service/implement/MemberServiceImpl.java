package se.lexicon.teaterwebapp.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;
import se.lexicon.teaterwebapp.model.Dto.MemberDto;

import se.lexicon.teaterwebapp.model.entity.Member;

import se.lexicon.teaterwebapp.repository.MemberRepository;
import se.lexicon.teaterwebapp.repository.StaffRepository;
import se.lexicon.teaterwebapp.service.MemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    ModelMapper mapper;
    MemberRepository memberRepository;

    public MemberServiceImpl(ModelMapper mapper, MemberRepository memberRepository) {
        this.mapper = mapper;
        this.memberRepository = memberRepository;
    }
    @Override

    public MemberDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Person Id should not be null");
        Member result = memberRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Person not found"));
        return mapper.map(result, MemberDto.class);
    }
    @Override

    public MemberDto findByEmail(String email) throws DataNotFoundException {
        if (email == null) throw new IllegalArgumentException("Email should not be null");
        Member result = memberRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("Person not found"));
        return mapper.map(result, MemberDto.class);
    }
    @Override
    public List<MemberDto> findAll() {
        List<Member> list = new ArrayList<>();
        memberRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> mapper.map(category, MemberDto.class)).collect(Collectors.toList());
    }
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public MemberDto create(MemberDto dto) throws DataDuplicateException, DataNotFoundException {
        if (dto == null) throw new IllegalArgumentException("Person data should not be null");
        if (dto.getId() != null) throw new IllegalArgumentException("id should be null");
        if(memberRepository.findByEmail(dto.getEmail()).isPresent()) throw new DataDuplicateException("Email is duplicate");
        Member entity = mapper.map(dto, Member.class);
        Member result = memberRepository.save(entity);

        return mapper.map(result, MemberDto.class);
    }
    @Override

    @Transactional(rollbackFor = {Exception.class})
    public MemberDto update(MemberDto dto) throws DataNotFoundException {
        System.out.println("dto = " + dto);
        if (dto == null) throw new IllegalArgumentException("Person data should not be null");
        if (dto.getId() == null) throw new IllegalArgumentException("id should not be null");

        findById(dto.getId());

        Member entity = mapper.map(dto, Member.class);
        Member result = memberRepository.save(entity);

        return mapper.map(result, MemberDto.class);
    }
    @Override

    public void delete(Integer id) throws DataNotFoundException {
        findById(id);
        memberRepository.deleteById(id);
    }


}
