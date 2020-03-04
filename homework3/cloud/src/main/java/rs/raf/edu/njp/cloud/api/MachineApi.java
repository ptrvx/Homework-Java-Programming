package rs.raf.edu.njp.cloud.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.edu.njp.cloud.domain.dto.MachineDto;
import rs.raf.edu.njp.cloud.domain.mappers.MachineMapper;
import rs.raf.edu.njp.cloud.service.MachineService;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/machine")
@RequiredArgsConstructor
public class MachineApi {

    private final MachineService machineService;
    private final MachineMapper machineMapper;

    @GetMapping
    public String test() {
        return "Hello world!!";
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
    public MachineDto create(@RequestParam String name) {
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
        } catch (NullPointerException e){}
        try{
            to = LocalDate.parse(dateTo).plusDays(1).atStartOfDay();
        } catch (NullPointerException e){}

        return machineMapper.map(machineService.search(name, status, from, to));
    }


}
