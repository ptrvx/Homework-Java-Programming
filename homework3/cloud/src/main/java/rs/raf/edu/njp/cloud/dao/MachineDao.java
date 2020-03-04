package rs.raf.edu.njp.cloud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.raf.edu.njp.cloud.domain.model.Machine;
import rs.raf.edu.njp.cloud.domain.model.MachineStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MachineDao extends JpaRepository<Machine, Long> {

    public Machine getById(Long id);

    public List<Machine> getMachinesByNameContainingAndActiveIsTrueAndStatusIn(String name, List<MachineStatus> statuses);

    public List<Machine> getMachinesByNameContainingAndActiveIsTrueAndStatusInAndDatetimeBetween(String name, List<MachineStatus> statuses, LocalDateTime dateFrom, LocalDateTime dateTo);

    public List<Machine> getMachinesByNameContainingAndActiveIsTrueAndStatusInAndDatetimeIsAfter(String name, List<MachineStatus> statuses, LocalDateTime dateFrom);

    public List<Machine> getMachinesByNameContainingAndActiveIsTrueAndStatusInAndDatetimeIsBefore(String name, List<MachineStatus> statuses, LocalDateTime dateTo);



}
