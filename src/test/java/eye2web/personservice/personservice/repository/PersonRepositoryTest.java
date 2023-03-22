package eye2web.personservice.personservice.repository;

import eye2web.personservice.personservice.model.dao.PersonEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void shouldStorePersonTest() throws Exception {

        final var placeHolderPersonOpt = personRepository.findById(1);
        final var placeHolderPerson = placeHolderPersonOpt.orElseThrow(() -> new Exception("Missing placeholder person"));

        final var person = PersonEntity.builder()
                .name("Harry test")
                .parent1(placeHolderPerson)
                .parent2(placeHolderPerson)
                .birthDate(LocalDate.of(1980, 1, 1))
                .build();

        final var savedResult = personRepository.save(person);
        
        Assertions.assertEquals("Harry test", savedResult.getName());
        Assertions.assertEquals(placeHolderPerson, savedResult.getParent1());
        Assertions.assertEquals(placeHolderPerson, savedResult.getParent2());
        Assertions.assertEquals(LocalDate.of(1980, 1, 1), savedResult.getBirthDate());
    }

}
