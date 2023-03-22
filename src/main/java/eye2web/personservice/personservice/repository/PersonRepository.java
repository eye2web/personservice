package eye2web.personservice.personservice.repository;

import eye2web.personservice.personservice.model.dao.PersonEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

    List<PersonEntity> findByParent1AndParent2(final PersonEntity parent1, final PersonEntity parent2);

    List<PersonEntity> findAll(final Sort sort);
}
