package se.lexicon.teaterwebapp.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.teaterwebapp.model.entity.ContactInformation;
import se.lexicon.teaterwebapp.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactInformationRepository extends CrudRepository<ContactInformation, Integer> {
    Optional<ContactInformation> findById(int id);

    void delete(ContactInformation contactInformation);
    List<ContactInformation> findAll();
    List<ContactInformation> findByCity(String city);

    List<ContactInformation> findByUser(User user);
}


