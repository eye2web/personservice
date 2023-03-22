package eye2web.personservice.personservice.model;

import eye2web.personservice.personservice.model.dao.PersonEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Person {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private LocalDate birthDate;
    @NonNull
    private PersonEntity parent1;
    @NonNull
    private PersonEntity parent2;
    @NonNull
    private List<Person> children;

    private Person partner;

    public Optional<Person> getPartner() {
        return Optional.ofNullable(partner);
    }
}
