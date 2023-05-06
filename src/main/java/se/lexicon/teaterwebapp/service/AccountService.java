package se.lexicon.teaterwebapp.service;


import se.lexicon.teaterwebapp.model.Dto.AccountDto;

public interface AccountService {
    boolean authenticate(String username, String password);
    AccountDto findByUsername(String username);
    AccountDto save(AccountDto loginDto);
}


