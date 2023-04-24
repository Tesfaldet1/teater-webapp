package se.lexicon.teaterwebapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.lexicon.teaterwebapp.model.entity.Staff;

import java.util.List;
import java.util.Optional;

@Repository

public interface StaffRepository extends CrudRepository<Staff, Integer> {
    Optional<Staff> findById(int id);
    Optional<Staff> findByEmail(String email);
    List<Staff> findByLastName(String lastName);
    List<Staff> findByFirstNameAndLastName(String firstName, String lastName);
}
