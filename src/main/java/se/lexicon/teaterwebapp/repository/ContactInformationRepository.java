package se.lexicon.teaterwebapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.teaterwebapp.model.entity.ContactInformation;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactInformationRepository extends CrudRepository<ContactInformation, Integer> {
    Optional<ContactInformation> findById(int id);

    void delete(ContactInformation contactInformation);
    List<ContactInformation> findAll();
}
