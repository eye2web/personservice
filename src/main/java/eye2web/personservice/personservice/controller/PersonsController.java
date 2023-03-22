package eye2web.personservice.personservice.controller;

import eye2web.personservice.personservice.model.response.PersonResponse;
import eye2web.personservice.personservice.service.PersonService;
import eye2web.personservice.personservice.util.Base64Utils;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/persons")
public class PersonsController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPersonListFiltered() {
        final var personResponseList = personService.getPersonsWith3ChildrenOneBelowAge18()
                .stream()
                .map(result -> modelMapper.map(result, PersonResponse.class)).
                collect(Collectors.toList());

        return ResponseEntity.ok(personResponseList);
    }

    /*
        Todo Nicer to use with content-type/application-csv instead of /csv endpoint
     */
    @GetMapping("/csv")
    public ResponseEntity<String> getPersonListAsBas64CSV() {
        final var personList = personService.getPersonsWith3ChildrenOneBelowAge18();
        final var csv = personService.convertToCSV(personList);
        return ResponseEntity.ok(Base64Utils.Encode(csv));
    }

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson() {

        throw new NotYetImplementedException();
    }

    @PutMapping
    public ResponseEntity<PersonResponse> updatePerson() {

        throw new NotYetImplementedException();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePerson() {

        throw new NotYetImplementedException();
    }

}
