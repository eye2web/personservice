package eye2web.personservice.personservice.service;

import eye2web.personservice.personservice.model.Person;
import eye2web.personservice.personservice.model.dao.PersonEntity;
import eye2web.personservice.personservice.repository.PersonRepository;
import eye2web.personservice.personservice.service.exception.IllegalQueryParameterValue;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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

    public List<Person> getAllPersons() {

        return Streamable.of(personRepository.findAll())
                .stream().map(this::toPerson)
                .collect(Collectors.toList());
    }

    public List<Person> getAllPersons(final String direction, final String field) {

        try {
            final var sort = Sort.by(Sort.Direction.fromString(direction), field);
            return personRepository.findAll(sort)
                    .stream().map(this::toPerson)
                    .collect(Collectors.toList());
        } catch (final IllegalArgumentException ex) {
            throw new IllegalQueryParameterValue("Direction query parameter should contain asc or desc");
        }
    }

    public String convertToCSV(final List<Person> personList) {

        final var headers = "id;name;birthday;parent1_id;parent1_name;parent2_id;parent2_name;partner_id;partner_name;child1_id;child1_name;child2_id;child2_name;child3_id;child3_name";

        final var strBuilder = new StringBuilder();
        strBuilder.append(headers);
        strBuilder.append(System.lineSeparator());
        personList.stream()
                .map(this::toCSVRow)
                .forEach(strBuilder::append);

        return strBuilder.toString();
    }

    private Person toPerson(final PersonEntity personEntity) {

        final var parent = modelMapper.map(personEntity, Person.class);
        parent.setChildren(getChildrenForPerson(personEntity));
        parent.setPartner(
                personEntity.getPartner()
                        .map(partner -> modelMapper.map(partner, Person.class))
                        .orElse(null)
        );

        return parent;
    }

    private List<Person> getChildrenForPerson(final PersonEntity personEntity) {
        final var childEntities = personEntity.getPartner().map(partner ->
                mergeLists(
                        personRepository.findByParent1AndParent2(personEntity, partner),
                        personRepository.findByParent1AndParent2(partner, personEntity)
                )
        ).orElseGet(() ->
                personRepository.findByParent1OrParent2(personEntity, personEntity)
        );

        return childEntities.stream()
                .map(childEntity -> modelMapper.map(childEntity, Person.class))
                .collect(Collectors.toList());
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

    private String toCSVRow(final Person person) {
        var rowBuilder = new StringBuilder();
        final var parentRow = String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;",
                person.getId(),
                person.getName(),
                person.getBirthDate().format(DateTimeFormatter.BASIC_ISO_DATE),
                person.getParent1().getId(),
                person.getParent1().getName(),
                person.getParent2().getId(),
                person.getParent2().getName(),
                person.getPartner().map(partner -> String.valueOf(partner.getId())).orElse(""),
                person.getPartner().map(Person::getName).orElse("")
        );

        final var childrenRowOpt = person.getChildren().stream().map(child ->
                String.format("%s;%s;", child.getId(), child.getName())
        ).reduce((identity, accumulator) ->
                identity += accumulator
        );

        rowBuilder.append(parentRow);
        childrenRowOpt.ifPresent((childrenRow -> rowBuilder.append(childrenRow, 0, childrenRow.length() - 1)));
        rowBuilder.append(System.lineSeparator());
        return rowBuilder.toString();
    }
}
