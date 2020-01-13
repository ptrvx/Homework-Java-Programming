package rs.raf.edu.njp.cloud.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private UUID uid;
    private MachineStatus status;
    private Boolean active;
    private LocalDateTime datetime;

    public Machine() {
    }

    public Machine(String name) {
        this.name = name;
        this.uid = UUID.randomUUID();
        this.status = MachineStatus.STOPPED;
        this.active = true;
        this.datetime = LocalDateTime.now();
    }

    public Machine(Long id, String name, UUID uid, MachineStatus status, Boolean active, LocalDateTime datetime) {
        this.id = id;
        this.uid = uid;
        this.status = status;
        this.active = active;
        this.datetime = datetime;
        this.name = name;
    }


}
