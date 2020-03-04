package rs.raf.edu.njp.cloud.domain.mappers;

import org.mapstruct.Mapper;
import rs.raf.edu.njp.cloud.domain.dto.MachineDto;
import rs.raf.edu.njp.cloud.domain.model.Machine;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MachineMapper {

    public abstract MachineDto map(Machine machine);

    public abstract List<MachineDto> map(List<Machine> machines);
}
