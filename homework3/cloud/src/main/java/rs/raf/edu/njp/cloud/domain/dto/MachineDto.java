package rs.raf.edu.njp.cloud.domain.dto;

import lombok.Data;
import rs.raf.edu.njp.cloud.domain.model.MachineStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MachineDto {

    private Long id;
    private String name;
    private UUID uid;
    private MachineStatus status;
    private Boolean active;
    private LocalDateTime datetime;

    public MachineDto() {
    }

    public MachineDto(Long id, String name, UUID uid, MachineStatus status, Boolean active, LocalDateTime datetime) {
        this.id = id;
        this.uid = uid;
        this.status = status;
        this.active = active;
        this.datetime = datetime;
        this.name = name;
    }

}
