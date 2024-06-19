package hackeru.fridarik.snipsnapp.controller;

import hackeru.fridarik.snipsnapp.dto.AuthDTO;
import hackeru.fridarik.snipsnapp.dto.BarberResponseDTO;
import hackeru.fridarik.snipsnapp.dto.CustomerResponseDTO;
import hackeru.fridarik.snipsnapp.dto.LoginDTO;
import hackeru.fridarik.snipsnapp.service.JwtService;
import hackeru.fridarik.snipsnapp.service.LoginServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginServiceImpl loginService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<AuthDTO<?>> login(
            @RequestBody @Valid LoginDTO dto
    ){
        String token = null;

        BarberResponseDTO d1 = loginService.loginBarber(dto);
        if (d1 != null) {
            token = jwtService.createToken(d1.getEmail());
            AuthDTO<BarberResponseDTO> authDTO = new AuthDTO<BarberResponseDTO>(d1, token);
            return ResponseEntity.ok(authDTO);
        }
        CustomerResponseDTO d2 = loginService.loginCustomer(dto);
        if (d2 != null) {
            token = jwtService.createToken(d2.getEmail());
            AuthDTO<CustomerResponseDTO> authDTO = new AuthDTO<CustomerResponseDTO>(d2, token);
            return ResponseEntity.ok(authDTO);
        }
        return ResponseEntity.badRequest().build();
    }
}
