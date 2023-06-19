package se.lexicon.teaterwebapp.service;

import se.lexicon.teaterwebapp.model.Dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> getAll();

    RoleDto findById(Integer roleId);

    RoleDto create (RoleDto roleDto);

    void update (RoleDto roleDto);

    void delete(Integer roleId);

}

