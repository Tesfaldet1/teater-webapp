package se.lexicon.teaterwebapp.service;


import se.lexicon.teaterwebapp.model.Dto.AccountDto;
import se.lexicon.teaterwebapp.model.Dto.UserDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> findAll();
    boolean authenticate(String username, String password);
    AccountDto findByUsername(String username);
    AccountDto save(AccountDto loginDto);
    List<AccountDto> findByEmail(String email);
}


