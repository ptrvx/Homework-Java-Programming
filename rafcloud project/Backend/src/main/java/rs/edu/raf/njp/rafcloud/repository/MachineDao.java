package rs.edu.raf.njp.rafcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.edu.raf.njp.rafcloud.domain.entity.Machine;
import rs.edu.raf.njp.rafcloud.domain.entity.MachineStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface MachineDao extends JpaRepository<Machine, Long> {

    public Machine getById(Long id);

    public List<Machine> getMachinesByCreated_UsernameAndActiveIsTrueAndNameContainingAndStatusIn(String username, String name, List<MachineStatus> statuses);

    public List<Machine> getMachinesByCreated_UsernameAndNameContainingAndActiveIsTrueAndStatusInAndDatetimeBetween(String username, String name, List<MachineStatus> statuses, LocalDateTime dateFrom, LocalDateTime dateTo);

    public List<Machine> getMachinesByCreated_UsernameAndNameContainingAndActiveIsTrueAndStatusInAndDatetimeIsAfter(String username, String name, List<MachineStatus> statuses, LocalDateTime dateFrom);

    public List<Machine> getMachinesByCreated_UsernameAndNameContainingAndActiveIsTrueAndStatusInAndDatetimeIsBefore(String username, String name, List<MachineStatus> statuses, LocalDateTime dateTo);


}
