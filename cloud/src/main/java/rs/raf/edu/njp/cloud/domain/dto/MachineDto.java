package rs.raf.edu.njp.cloud.domain.dto;

import lombok.Data;
import rs.raf.edu.njp.cloud.domain.model.MachineStatus;

import java.util.UUID;

@Data
public class MachineDto {

    private Long id;
    private UUID uid;
    private MachineStatus machineStatus;
    private Boolean active;

    public MachineDto() {
    }

    public MachineDto(Long id, UUID uid, MachineStatus machineStatus, Boolean active) {
        this.id = id;
        this.uid = uid;
        this.machineStatus = machineStatus;
        this.active = active;
    }

}
