package se.lexicon.teaterwebapp.service.implement;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;
import se.lexicon.teaterwebapp.model.Dto.RoleDto;
import se.lexicon.teaterwebapp.model.entity.Role;
import se.lexicon.teaterwebapp.repository.RoleRepository;
import se.lexicon.teaterwebapp.service.RoleService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<RoleDto> getAll() {
        List<Role> roleList = roleRepository.findAllByOrderByIdDesc();
       /*return roleList.stream()
               .map(role -> new RoleDto(role.getId(), role.getName()))
               .collect(Collectors.toList());

        */

        return modelMapper.map(roleList, new TypeToken<List<RoleDto>>(){}.getType());
    }

    @Override
    public RoleDto findById(Integer roleId) {
        if (roleId== null) throw new DataNotFoundException("role data id was not found");
        Optional<Role> optionalRole =roleRepository.findById(roleId);
        if(!optionalRole.isPresent())throw new DataNotFoundException("role id was not valid");
        Role entity = optionalRole.get();// convert the entity to the dto
        // RoleDto dto = new RoleDto(entity.getId(), entity.getName()); use modelMapper instead
        return modelMapper.map(entity, RoleDto.class);
        //return  dto;

    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        if(roleDto ==null) throw  new IllegalStateException("role data was null");
        if(roleDto.getId()!=0) throw new IllegalStateException("role id data should be null");
        //Role convertedToEntity = new Role(roleDto.getName());
        Role createdEntity = roleRepository.save(modelMapper.map(roleDto, Role.class));
        // RoleDto convertedToDto = new RoleDto(createdEntity.getId(),createdEntity.getName());
        return modelMapper.map(createdEntity,RoleDto.class );
    }

    @Override
    public void update(RoleDto roleDto) {
        if (roleDto == null) throw new IllegalArgumentException("role data was null");
        if (roleDto.getId() == 0) throw new IllegalArgumentException("role id should not be zero");

        if (!roleRepository.findById(roleDto.getId()).isPresent())
            throw new DataNotFoundException("data not found error");

        if (roleRepository.findByName(roleDto.getName()).isPresent())
            throw new DataDuplicateException("duplicate error");
        // Role convertToEntity = new Role(roleDto.getId(), roleDto.getName());
        roleRepository.save(modelMapper.map(roleDto, Role.class));
    }

    @Override
    public void delete(Integer roleId) {
        RoleDto roleDto = findById(roleId);
        if(roleDto==null) throw new DataNotFoundException("the role id was valid");
        roleRepository.deleteById(roleId);

    }
}


