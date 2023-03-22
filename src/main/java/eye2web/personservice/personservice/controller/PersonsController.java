package eye2web.personservice.personservice.controller;

import eye2web.personservice.personservice.model.response.PersonResponse;
import eye2web.personservice.personservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/persons")
public class PersonsController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPersonList() {
        final var personResponseList = personService.getPersonsWith3ChildrenOneBelowAge18().stream()
                .map(result -> modelMapper.map(result, PersonResponse.class)).
                collect(Collectors.toList());

        return ResponseEntity.ok(personResponseList);
    }

}
