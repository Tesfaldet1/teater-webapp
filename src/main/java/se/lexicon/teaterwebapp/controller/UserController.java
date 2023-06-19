package se.lexicon.teaterwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import se.lexicon.teaterwebapp.model.Dto.AccountDto;
import se.lexicon.teaterwebapp.model.Dto.UserDto;
import se.lexicon.teaterwebapp.model.entity.User;
import se.lexicon.teaterwebapp.repository.UserRepository;
import se.lexicon.teaterwebapp.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        System.out.println("Get All API has been executed!");
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto dto) {
        System.out.println("USERNAME: " + dto.getUsername());
        UserDto serviceResult = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResult);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Map<String, Object>> findByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }

    @PutMapping("/disable")
    public ResponseEntity<Void> disableUserByUsername(@RequestParam("username") String username) {
        userService.disableUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/ register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        User savedUser = null;
        ResponseEntity response= null;
        try{
            String hashPwd= passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPwd);
            savedUser = userRepository.save(user);
            if(savedUser.getUserId()>0){
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        }catch (Exception ex){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }


}