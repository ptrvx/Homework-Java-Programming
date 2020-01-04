package rs.raf.edu.njp.cloud.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uid;
    private MachineStatus status;
    private Boolean active;

    public Machine() {
    }

    public Machine(Long id, UUID uid, MachineStatus status, Boolean active) {
        this.id = id;
        this.uid = uid;
        this.status = status;
        this.active = active;
    }


}
