package se.lexicon.teaterwebapp.service;


import se.lexicon.teaterwebapp.model.Dto.UserDto;

import java.util.Map;

public interface UserService {
    UserDto register(UserDto userDto);
    Map<String, Object> findByUsername(String username);
    void disableUserByUsername(String username);
}

