package eye2web.personservice.personservice.controller;

import eye2web.personservice.personservice.model.response.PersonResponse;
import eye2web.personservice.personservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
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
        return ResponseEntity.ok(toBase64(csv));
    }

    private String toBase64(final String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

}
