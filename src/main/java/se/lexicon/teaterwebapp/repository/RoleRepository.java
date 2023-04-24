package se.lexicon.teaterwebapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.teaterwebapp.model.entity.Role;

import java.util.List;
import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(String name);

    List<Role>  findAllByOrderByIdDesc();
}
