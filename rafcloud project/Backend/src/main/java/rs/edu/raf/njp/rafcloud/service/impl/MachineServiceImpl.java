package rs.edu.raf.njp.rafcloud.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.edu.raf.njp.rafcloud.domain.entity.Machine;
import rs.edu.raf.njp.rafcloud.domain.entity.MachineStatus;
import rs.edu.raf.njp.rafcloud.domain.entity.User;
import rs.edu.raf.njp.rafcloud.domain.entity.UserEntity;
import rs.edu.raf.njp.rafcloud.repository.MachineDao;
import rs.edu.raf.njp.rafcloud.repository.UserRepository;
import rs.edu.raf.njp.rafcloud.service.MachineService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class MachineServiceImpl implements MachineService {

    private final MachineDao machineDao;
    private final UserRepository userRepository;

    private Set<Long> working = Collections.newSetFromMap(new ConcurrentHashMap<Long, Boolean>());
    private ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public Boolean start(Long id) {
        if (working.contains(id))
            return false;
        Machine machine = machineDao.getById(id);
        if (machine == null || !machine.getStatus().equals(MachineStatus.STOPPED))
            return false;
        if (!machine.getCreated().getUsername().equals(username()))
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
        if (!machine.getCreated().getUsername().equals(username()))
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
        if (machine == null || !machine.getStatus().equals(MachineStatus.STOPPED))
            return false;
        if (!machine.getCreated().getUsername().equals(username()))
            return false;

        working.add(id);
        executor.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                machine.setStatus(MachineStatus.STOPPED);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
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
        UserEntity user = userRepository.findByUsername(username());
        Machine machine = new Machine(name, user);
        return this.machineDao.save(machine);
    }

    @Override
    public Boolean destroy(Long id) {
        Machine machine = machineDao.getById(id);
        if (machine == null)
            return false;
        if (!machine.getCreated().getUsername().equals(username()))
            return false;
        machine.setActive(false);
        machineDao.save(machine);
        return true;
    }

    @Override
    public List<Machine> search(String name, List<String> status, LocalDateTime dateFrom, LocalDateTime dateTo) {
        String username = username();
        System.out.println("++++++++++" + username + " is searching machines: " + "name: " + name + " , status: " + status + " , dateFrom: " + dateFrom + " , dateTo: " + dateTo);

        List<MachineStatus> statuses = new ArrayList<>();
        try {
            for (String s : status) {
                statuses.add(MachineStatus.valueOf(s));
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            statuses.add(MachineStatus.RUNNING);
            statuses.add(MachineStatus.STOPPED);
        }

        if (dateFrom != null && dateTo != null) {
            return new ArrayList<>(machineDao.getMachinesByCreated_UsernameAndNameContainingAndActiveIsTrueAndStatusInAndDatetimeBetween(username, name, statuses, dateFrom, dateTo));
        } else if (dateFrom != null) {
            return new ArrayList<>(machineDao.getMachinesByCreated_UsernameAndNameContainingAndActiveIsTrueAndStatusInAndDatetimeIsAfter(username, name, statuses, dateFrom));
        } else if (dateTo != null) {
            return new ArrayList<>(machineDao.getMachinesByCreated_UsernameAndNameContainingAndActiveIsTrueAndStatusInAndDatetimeIsBefore(username, name, statuses, dateTo));
        } else {
            return new ArrayList<>(machineDao.getMachinesByCreated_UsernameAndActiveIsTrueAndNameContainingAndStatusIn(username, name, statuses));
        }
    }

    private String username() {
        try {
            return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        } catch (NullPointerException e) {
            System.out.println("User not found error.");
        }
        return "anonymousUser";
    }

}
