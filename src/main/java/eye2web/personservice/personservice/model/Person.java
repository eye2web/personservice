package eye2web.personservice.personservice.model;

import eye2web.personservice.personservice.model.dao.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Person {
    private int id;
    private String name;
    private LocalDate birthDate;
    private PersonEntity parent1;
    private PersonEntity parent2;
    private List<Person> children;

    private Person partner;

    public Optional<Person> getPartner() {
        return Optional.ofNullable(partner);
    }
}
