package dat22v2.tb.travelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Travel {
    @Id
    private Long id;

    private String name;




}
