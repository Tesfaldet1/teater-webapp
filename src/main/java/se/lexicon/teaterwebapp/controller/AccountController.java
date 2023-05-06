package se.lexicon.teaterwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.teaterwebapp.model.Dto.AccountDto;
import se.lexicon.teaterwebapp.service.AccountService;

@RestController
@RequestMapping("/api/login")
public class AccountController {

    @Autowired
    private AccountService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AccountDto loginDto) {
        boolean authenticated = loginService.authenticate(loginDto.getUsername(), loginDto.getPassword());
        if (authenticated) {
            AccountDto login = loginService.findByUsername(loginDto.getUsername());
            return ResponseEntity.ok(login);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDto> register(@RequestBody AccountDto loginDto) {
        AccountDto registeredLogin = loginService.save(loginDto);
        return ResponseEntity.ok(registeredLogin);
    }
}


