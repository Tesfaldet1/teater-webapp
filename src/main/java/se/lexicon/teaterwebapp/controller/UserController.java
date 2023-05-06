package se.lexicon.teaterwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.lexicon.teaterwebapp.model.Dto.UserDto;
import se.lexicon.teaterwebapp.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

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


}