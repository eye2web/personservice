package eye2web.personservice.personservice.service;

import eye2web.personservice.personservice.model.Person;
import eye2web.personservice.personservice.model.dao.PersonEntity;
import eye2web.personservice.personservice.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    /*
        Get list of all persons who have a partner and three children with that partner and one of the children has an age below 18
     */
    public List<Person> getPersonsWith3ChildrenOneBelowAge18() {

        return Streamable.of(personRepository.findAll())
                .filter(personEntity -> personEntity.getPartner().isPresent())
                .map(this::toPerson)
                .filter(person ->
                        person.getChildren().size() == 3 &&
                                person.getChildren().stream().anyMatch(child -> getAge(child.getBirthDate()) < 18)
                ).stream().toList();
    }

    private Person toPerson(final PersonEntity personEntity) {
        final var childEntities = mergeLists(
                personRepository.findByParent1AndParent2(personEntity, personEntity.getPartner().get()),
                personRepository.findByParent1AndParent2(personEntity.getPartner().get(), personEntity)
        );

        final var children = childEntities.stream()
                .map(childEntity -> modelMapper.map(childEntity, Person.class))
                .collect(Collectors.toList());

        final var parent = modelMapper.map(personEntity, Person.class);
        parent.setChildren(children);
        parent.setPartner(
                personEntity.getPartner()
                        .map(partner -> modelMapper.map(partner, Person.class))
                        .orElse(null)
        );

        return parent;
    }

    /*
        Use mergeLists to make sure that it also works if parents are swapped
    */
    private List<PersonEntity> mergeLists(final List<PersonEntity> listOne, final List<PersonEntity> listTwo) {
        final var listTwoCopy = new ArrayList<>(listTwo);
        listTwoCopy.removeAll(listOne);
        listOne.addAll(listTwoCopy);
        return listOne;
    }

    private int getAge(final LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
