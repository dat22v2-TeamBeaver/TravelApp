package dat22v2.tb.travelapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Travel {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
