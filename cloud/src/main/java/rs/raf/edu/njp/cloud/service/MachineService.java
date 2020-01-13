package rs.raf.edu.njp.cloud.service;

import rs.raf.edu.njp.cloud.domain.dto.MachineDto;
import rs.raf.edu.njp.cloud.domain.model.Machine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MachineService {

    public Boolean start(Long id);

    public Boolean stop(Long id);

    public Boolean restart(Long id);

    public Machine create(String name);

    public Boolean destroy(Long id);

    public List<Machine> search(String name, List<String> status, LocalDateTime dateFrom, LocalDateTime dateTo);

}
