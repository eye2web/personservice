package eye2web.personservice.personservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonResponse {
    private int id;
    private String name;
    private LocalDate birthDate;
    private ParentResponse parent1;
    private ParentResponse parent2;
    private List<PersonResponse> children;
    private ParentResponse partner;
}
