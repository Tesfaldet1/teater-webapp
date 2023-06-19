package se.lexicon.teaterwebapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import se.lexicon.teaterwebapp.model.entity.Member;
import se.lexicon.teaterwebapp.model.entity.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    List<User> findByEmail(String email);

   @Modifying
   @Query("update User u set u.expired= :expired where u.username = :username")
    void updateExpiredByUsername(@Param("username") String username, @Param("expired") boolean expired);


}

