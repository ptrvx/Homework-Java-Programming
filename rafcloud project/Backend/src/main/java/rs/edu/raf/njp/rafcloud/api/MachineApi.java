package rs.edu.raf.njp.rafcloud.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.njp.rafcloud.domain.dto.MachineDto;
import rs.edu.raf.njp.rafcloud.domain.dto.MachineMapper;
import rs.edu.raf.njp.rafcloud.domain.entity.User;
import rs.edu.raf.njp.rafcloud.domain.entity.UserEntity;
import rs.edu.raf.njp.rafcloud.service.MachineService;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class MachineApi {

    private final MachineService machineService;
    private final MachineMapper machineMapper;

    @GetMapping("/test")
    public String hello() {
        return "Hello, Rest test works!";
    }

    @PostMapping("/start")
    public ResponseEntity<?> start(@RequestParam Long id) {
        if (machineService.start(id)) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine unavailable.");
        }
    }

    @PostMapping("/restart")
    public ResponseEntity<?> restart(@RequestParam Long id) {
        if (machineService.restart(id)) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine unavailable.");
        }
    }

    @PostMapping("/stop")
    public ResponseEntity<?> stop(@RequestParam Long id) {
        if (machineService.stop(id)) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine unavailable.");
        }
    }

    @PostMapping("/create")
    public MachineDto create(@RequestBody String name) {
        return machineMapper.map(machineService.create(name));
    }

    @DeleteMapping
    public ResponseEntity<?> destroy(@RequestParam Long id) {
        if (machineService.destroy(id)) {
            return ResponseEntity.ok("OK");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Machine unavailable.");
        }
    }

    @GetMapping("/search")
    public List<MachineDto> search(@RequestParam(defaultValue = "") String name, @RequestParam(required = false) List<String> status, @RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo) {
        LocalDateTime from = null, to = null;
        try {
            from = LocalDate.parse(dateFrom).atStartOfDay();
        } catch(NullPointerException ignored) {}
        try {
            to = LocalDate.parse(dateTo).plusDays(1).atStartOfDay();
        } catch (NullPointerException ignored) {}

        return machineMapper.map(machineService.search(name, status, from, to));
    }

}
