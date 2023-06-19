package se.lexicon.teaterwebapp.service;


import se.lexicon.teaterwebapp.model.Dto.StaffDto;
import se.lexicon.teaterwebapp.model.Dto.UserDto;
import se.lexicon.teaterwebapp.model.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDto register(UserDto userDto);
    Map<String, Object> findByUsername(String username);
    void disableUserByUsername(String username);
    List<UserDto> findAll();
    List<UserDto> findByEmail(String email);
}

