package hackeru.fridarik.snipsnapp.controller;

import hackeru.fridarik.snipsnapp.dto.AuthDTO;
import hackeru.fridarik.snipsnapp.dto.BarberCreateDTO;
import hackeru.fridarik.snipsnapp.dto.BarberResponseDTO;
import hackeru.fridarik.snipsnapp.dto.EntityListDTO;
import hackeru.fridarik.snipsnapp.service.BarberService;
import hackeru.fridarik.snipsnapp.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barbers")
@RequiredArgsConstructor
public class BarberController {

    private final BarberService barberService;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<EntityListDTO<BarberResponseDTO>> getAllBarbers(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "4") int pageSize,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String... sortBy
    ){
        return ResponseEntity.ok(barberService.getAllBarbers(pageNo, pageSize, sortDir, sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberResponseDTO> getBarberById(@PathVariable long id){
        return ResponseEntity.ok(barberService.getBarberById(id));
    }

    @PostMapping
    public ResponseEntity<AuthDTO<BarberResponseDTO>> createBarber(
            @RequestBody @Valid BarberCreateDTO dto,
            UriComponentsBuilder uriBuilder
    ){
        var response = barberService.createBarber(dto);
        String token = jwtService.createToken(response.getEmail());
        AuthDTO<BarberResponseDTO> authDTO = new AuthDTO<BarberResponseDTO>(response, token);
        var uri = uriBuilder.path("/api/v1/barbers/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(authDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarberResponseDTO> updateBarberById(
            @PathVariable long id,
            @RequestBody @Valid BarberCreateDTO dto
    ){
        return ResponseEntity.ok(barberService.updateBarberInfo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BarberResponseDTO> deleteBarberById(@PathVariable long id){
        return ResponseEntity.ok(barberService.deleteBarber(id));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<BarberResponseDTO>> searchBarber(@PathVariable String name){
        return ResponseEntity.ok(barberService.searchByName(name));
    }
}
