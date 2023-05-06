package se.lexicon.teaterwebapp.repository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import se.lexicon.teaterwebapp.model.Dto.AccountDto;
import se.lexicon.teaterwebapp.model.entity.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
    List<Account> findByExpired(boolean expired);
    List<Account> findByLocked(boolean locked);
}


