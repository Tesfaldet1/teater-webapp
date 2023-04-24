package se.lexicon.teaterwebapp.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.teaterwebapp.model.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository

public interface MemberRepository extends CrudRepository<Member, Integer> {
    Optional<Member> findById(int id);
    Optional<Member> findByEmail(String email);
    List<Member> findByLastName(String lastName);
    List<Member> findByFirstNameAndLastName(String firstName, String lastName);
}
