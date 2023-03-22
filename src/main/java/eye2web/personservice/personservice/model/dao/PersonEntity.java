package eye2web.personservice.personservice.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(generator = "person_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "person_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private String name;
    private LocalDate birthDate;
    @ManyToOne
    @JoinColumn(name = "parent_1_ID")
    private PersonEntity parent1;
    @ManyToOne
    @JoinColumn(name = "parent_2_ID")
    private PersonEntity parent2;

    @Setter
    @ManyToOne
    @JoinColumn(name = "partner_ID")
    private PersonEntity partner;

    public Optional<PersonEntity> getPartner() {
        return Optional.ofNullable(partner);
    }

}
