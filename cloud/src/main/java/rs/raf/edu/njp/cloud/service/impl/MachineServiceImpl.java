package rs.raf.edu.njp.cloud.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.raf.edu.njp.cloud.dao.MachineDao;
import rs.raf.edu.njp.cloud.domain.dto.MachineDto;
import rs.raf.edu.njp.cloud.domain.mappers.MachineMapper;
import rs.raf.edu.njp.cloud.domain.model.Machine;
import rs.raf.edu.njp.cloud.domain.model.MachineStatus;
import rs.raf.edu.njp.cloud.service.MachineService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private final MachineDao machineDao;

    private HashSet<Long> working = new HashSet<>();
    private ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public Boolean start(Long id) {
        if (working.contains(id))
            return false;
        Machine machine = machineDao.getById(id);
        if (machine == null || !machine.getStatus().equals(MachineStatus.STOPPED))
            return false;

        working.add(id);
        executor.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                working.remove(id);
                machine.setStatus(MachineStatus.RUNNING);
                machineDao.save(machine);
            }
        });

        return true;
    }

    @Override
    public Boolean stop(Long id) {
        if (working.contains(id))
            return false;
        Machine machine = machineDao.getById(id);
        if (machine == null || !machine.getStatus().equals(MachineStatus.RUNNING))
            return false;

        working.add(id);
        executor.submit(() -> {
           try {
               Thread.sleep(10000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               working.remove(id);
               machine.setStatus(MachineStatus.STOPPED);
               machineDao.save(machine);
           }
        });

        return true;
    }

    @Override
    public Boolean restart(Long id) {
        if (working.contains(id))
            return false;
        Machine machine = machineDao.getById(id);
        if (machine == null || !machine.getStatus().equals(MachineStatus.RUNNING))
            return false;

        working.add(id);
        executor.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            } finally {
                machine.setStatus(MachineStatus.STOPPED);
            }

            try {
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            } finally {
                machine.setStatus(MachineStatus.RUNNING);
                working.remove(id);
            }
        });

        return true;
    }

    @Override
    public Machine create(String name) {
        Machine machine = new Machine(name);
        return this.machineDao.save(machine);
    }

    @Override
    public Boolean destroy(Long id) {
        Machine machine = machineDao.getById(id);
        if (machine == null)
            return false;
        machine.setActive(false);
        machineDao.save(machine);
        return true;
    }

    @Override
    public List<Machine> search(String name, List<String> status, LocalDateTime dateFrom, LocalDateTime dateTo) {

        System.out.println("name: " + name + " , status: " + status + " , dateFrom: " + dateFrom + " , dateTo: " + dateTo);

        List<MachineStatus> statuses = new ArrayList<>();


        try {
            for (String s : status) {
                statuses.add(MachineStatus.valueOf(s));
            }
        } catch (IllegalArgumentException |  NullPointerException e) {
            statuses.add(MachineStatus.RUNNING);
            statuses.add(MachineStatus.STOPPED);
        }
        

        if (dateFrom != null && dateTo != null)
            return new ArrayList<>(machineDao.getMachinesByNameContainingAndActiveIsTrueAndStatusInAndDatetimeBetween(name, statuses, dateFrom, dateTo));
        else if (dateFrom != null && dateTo == null)
            return new ArrayList<>(machineDao.getMachinesByNameContainingAndActiveIsTrueAndStatusInAndDatetimeIsAfter(name, statuses, dateFrom));
        else if (dateFrom == null && dateTo != null)
            return new ArrayList<>(machineDao.getMachinesByNameContainingAndActiveIsTrueAndStatusInAndDatetimeIsBefore(name, statuses, dateTo));
        else
            return new ArrayList<>(machineDao.getMachinesByNameContainingAndActiveIsTrueAndStatusIn(name, statuses));

    }
}
