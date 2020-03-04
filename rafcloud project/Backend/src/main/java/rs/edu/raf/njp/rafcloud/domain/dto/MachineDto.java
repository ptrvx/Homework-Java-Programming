package rs.edu.raf.njp.rafcloud.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import rs.edu.raf.njp.rafcloud.domain.entity.MachineStatus;
import rs.edu.raf.njp.rafcloud.domain.entity.UserEntity;

import java.time.LocalDateTime;

@Data
public class MachineDto {

    private Long id;
    private String name;
    private String uid;
    private MachineStatus status;
    private Boolean active;
    private LocalDateTime datetime;
    @JsonIgnore
    private UserEntity created;

    public MachineDto() {
    }

    public MachineDto(Long id, String name, String uid, MachineStatus status, Boolean active, LocalDateTime datetime) {
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.status = status;
        this.active = active;
        this.datetime = datetime;
    }

}
