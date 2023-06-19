package se.lexicon.teaterwebapp.repository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import se.lexicon.teaterwebapp.model.entity.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAll();
    Optional<Account> findByUsername(String username);
    List<Account> findByEmail(String email);
   // List<Account> findByExpired(boolean expired);
    //List<Account> findByLocked(boolean locked);
}


