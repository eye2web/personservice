package eye2web.personservice.personservice.model.request;

import eye2web.personservice.personservice.model.Person;
import eye2web.personservice.personservice.model.dao.PersonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonRequest {

    @NonNull
    private String name;
    @NonNull
    private LocalDate birthDate;
    @NonNull
    private PersonEntity parent1;
    @NonNull
    private PersonEntity parent2;

    private List<Integer> childrenIds = new ArrayList<>();

    private Person partner;
}
