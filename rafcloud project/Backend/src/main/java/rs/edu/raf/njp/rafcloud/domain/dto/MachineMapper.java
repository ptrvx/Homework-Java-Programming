package rs.edu.raf.njp.rafcloud.domain.dto;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import rs.edu.raf.njp.rafcloud.domain.entity.Machine;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MachineMapper {

    @Named("mapWithoutCreated")
    @Mapping(target = "created", ignore = true)
    public abstract MachineDto map(Machine machine);

    @IterableMapping(qualifiedByName = "mapWithoutCreated")
    public abstract List<MachineDto> map(List<Machine> machines);
}
