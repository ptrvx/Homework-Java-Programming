package rs.edu.raf.njp.rafcloud.domain.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "uid", nullable = false)
    private String uid;

    @Column(name = "status", nullable = false)
    private MachineStatus status;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity created;

    public Machine() {
    }

    public Machine(String name, UserEntity created) {
        this.name = name;
        this.uid = UUID.randomUUID().toString();
        this.status = MachineStatus.STOPPED;
        this.active = true;
        this.datetime = LocalDateTime.now();
        this.created = created;
    }

    public Machine(Long id, String name, String uid, MachineStatus status, Boolean active, LocalDateTime datetime, UserEntity created) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.status = status;
        this.active = active;
        this.datetime = datetime;
        this.created = created;
    }
}
