package eye2web.personservice.personservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParentResponse {
    private int id;
    private String name;
    private LocalDate birthDate;
}
