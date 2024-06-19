package hackeru.fridarik.snipsnapp.controller;

import hackeru.fridarik.snipsnapp.dto.*;
import hackeru.fridarik.snipsnapp.service.AppointmentServiceImpl;
import hackeru.fridarik.snipsnapp.service.BarberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    /*@GetMapping
    public ResponseEntity<BarberListDTO> getAllAppointments(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String... sortBy
    ){
        return ResponseEntity.ok(barberService.getAllBarbers(pageNo, pageSize, sortDir, sortBy));
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable long id){
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/barbers/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentByBarber(@PathVariable long id){
        return ResponseEntity.ok(appointmentService.getAppointmentByBarber(id));
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentByCustomer(@PathVariable long id){
        return ResponseEntity.ok(appointmentService.getAppointmentByCustomer(id));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(
            @RequestBody @Valid AppointmentCreateDTO dto,
            UriComponentsBuilder uriBuilder
    ){
        var response = appointmentService.createAppointment(dto);

        var uri = uriBuilder.path("/api/v1/appointment/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointmentById(
            @PathVariable long id,
            @RequestBody @Valid AppointmentCreateDTO dto
    ){
        return ResponseEntity.ok(appointmentService.updateAppointmentInfo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> deleteAppointmentById(@PathVariable long id){
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}
