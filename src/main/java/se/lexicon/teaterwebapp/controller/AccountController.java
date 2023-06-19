package se.lexicon.teaterwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import se.lexicon.teaterwebapp.model.Dto.AccountDto;
import se.lexicon.teaterwebapp.model.Dto.StaffDto;
import se.lexicon.teaterwebapp.model.Dto.UserDto;
import se.lexicon.teaterwebapp.model.entity.Account;
import se.lexicon.teaterwebapp.model.entity.User;
import se.lexicon.teaterwebapp.repository.UserRepository;
import se.lexicon.teaterwebapp.service.AccountService;

import java.util.List;

@RestController
//@RequestMapping("/api/login")
//@CrossOrigin("*")
public class AccountController {

    @Autowired
    private AccountService loginService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        System.out.println("Get All API has been executed!");
        return ResponseEntity.status(HttpStatus.OK).body(loginService.findAll());
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AccountDto user) {
        AccountDto savedUser = null;
        ResponseEntity<String> response = null;
        try {
            String hashPwd = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPwd);
            savedUser = loginService.save(user);
            if (savedUser != null) {
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to " + ex.getMessage());
        }
        return response;
    }

    @RequestMapping("/api/user")
    public AccountDto getUserDetailsAfterLogin(Authentication authentication) {
        List<AccountDto> customers = loginService.findByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }

    }
}


