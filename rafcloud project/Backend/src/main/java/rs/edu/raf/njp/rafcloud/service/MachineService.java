package rs.edu.raf.njp.rafcloud.service;

import rs.edu.raf.njp.rafcloud.domain.entity.Machine;
import rs.edu.raf.njp.rafcloud.domain.entity.UserEntity;

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
