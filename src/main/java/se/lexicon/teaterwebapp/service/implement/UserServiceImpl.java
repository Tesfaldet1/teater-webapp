package se.lexicon.teaterwebapp.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.teaterwebapp.Exception.DataDuplicateException;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;

import se.lexicon.teaterwebapp.model.Dto.StaffDto;
import se.lexicon.teaterwebapp.model.Dto.UserDto;
import se.lexicon.teaterwebapp.model.entity.Staff;
import se.lexicon.teaterwebapp.model.entity.User;
import se.lexicon.teaterwebapp.repository.RoleRepository;
import se.lexicon.teaterwebapp.repository.UserRepository;
import se.lexicon.teaterwebapp.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public UserDto register(UserDto userDto) {
        // Check for null userDto
        if (userDto == null) throw new IllegalArgumentException("UserDto data was null");

        // Check for null username or password
        if (userDto.getUsername() == null || userDto.getPassword() == null)
            throw new IllegalArgumentException("Username or password was null");

        // Check for duplicate username
        if (userRepository.existsByUsername(userDto.getUsername()))
            throw new DataDuplicateException("Duplicate username error");

        // Convert UserDto to User entity
        User user = modelMapper.map(userDto, User.class);

        // Save User entity to database
        User savedUser = userRepository.save(user);

        // Convert saved User entity to UserDto
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

        return savedUserDto;
    }

    @Override
    public Map<String, Object> findByUsername(String username) {
        // Check for null username
        if (username == null) throw new IllegalArgumentException("Username was null");

        // Find User entity by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("Username not found error"));

        // Create a Map to store response
        Map<String, Object> response = new HashMap<>();

        // Add roles to the response
        response.put("roles", user.getRoles());

        return response;
    }
    @Override
    public List<UserDto> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void disableUserByUsername(String username) {
        // Check for null username
        if (username == null) throw new IllegalArgumentException("Username was null");

        // Check if username exists in database
        if (!userRepository.existsByUsername(username))
            throw new DataNotFoundException("Username not found error");

        // Update user's expired status
        userRepository.updateExpiredByUsername(username, true);
    }
    @Override
    public List<UserDto> findByEmail(String email) throws DataNotFoundException {
        if (email == null) {
            throw new IllegalArgumentException("Email should not be null");
        }
        List<User> userList = userRepository.findByEmail(email);
        if (userList.isEmpty()) {
            throw new DataNotFoundException("No staff found with the provided email");
        }
        return userList.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}



