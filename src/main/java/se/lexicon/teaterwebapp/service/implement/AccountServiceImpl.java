package se.lexicon.teaterwebapp.service.implement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.lexicon.teaterwebapp.Exception.DataNotFoundException;
import se.lexicon.teaterwebapp.model.Dto.AccountDto;
import se.lexicon.teaterwebapp.model.Dto.UserDto;
import se.lexicon.teaterwebapp.model.entity.Account;
import se.lexicon.teaterwebapp.model.entity.User;
import se.lexicon.teaterwebapp.repository.AccountRepository;
import se.lexicon.teaterwebapp.service.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository loginRepository;
    @Autowired
    private  ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository loginRepository, ModelMapper modelMapper) {
        this.loginRepository = loginRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AccountDto> findAll() {
        List<Account> list = new ArrayList<>();
        loginRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category ->
                modelMapper.map(category, AccountDto.class)).collect(Collectors.toList());
    }

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
    @Override
    public List<AccountDto> findByEmail(String email) throws DataNotFoundException {
        if (email == null) {
            throw new IllegalArgumentException("Email should not be null");
        }
        List<Account> userList = loginRepository.findByEmail(email);
        if (userList.isEmpty()) {
            throw new DataNotFoundException("No staff found with the provided email");
        }
        return userList.stream()
                .map(user -> modelMapper.map(user, AccountDto.class))
                .collect(Collectors.toList());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


