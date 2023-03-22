package eye2web.personservice.personservice.service;

import eye2web.personservice.personservice.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Test
    public void shouldReturnListWith3ChildrenOneBelowAge18Test() {
        final var personList = personService.getPersonsWith3ChildrenOneBelowAge18();

        Assertions.assertEquals(2, personList.size());
        Assertions.assertEquals(3, personList.get(0).getChildren().size());
        Assertions.assertEquals(3, personList.get(1).getChildren().size());
    }

}
