package se.lexicon.teaterwebapp.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.lexicon.teaterwebapp.model.Dto.AccountDto;
import se.lexicon.teaterwebapp.model.entity.Account;
import se.lexicon.teaterwebapp.repository.AccountRepository;
import se.lexicon.teaterwebapp.service.AccountService;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository loginRepository;




    @Override
    public boolean authenticate(String username, String password) {
        Optional<Account> optionalLogin = loginRepository.findByUsername(username);
        if (optionalLogin.isPresent()) {
            Account login = optionalLogin.get();
            return passwordEncoder().matches(password, login.getPassword());
        }
        return false;
    }

    @Override
    public AccountDto findByUsername(String username) {
        Optional<Account> optionalLogin = loginRepository.findByUsername(username);
        if (optionalLogin.isPresent()) {
            Account login = optionalLogin.get();
            return new AccountDto(login.getUsername(),login.getPassword());
        }
        return null;
    }

    @Override
    public AccountDto save(AccountDto loginDto) {
        Account login = new Account(loginDto.getUsername(), passwordEncoder().encode(loginDto.getPassword()));
        login = loginRepository.save(login);
        return new AccountDto(login.getUsername(), login.getPassword());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


