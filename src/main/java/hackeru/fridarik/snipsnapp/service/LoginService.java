package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.BarberResponseDTO;
import hackeru.fridarik.snipsnapp.dto.CustomerResponseDTO;
import hackeru.fridarik.snipsnapp.dto.LoginDTO;

public interface LoginService {
    BarberResponseDTO loginBarber(LoginDTO dto);
    CustomerResponseDTO loginCustomer(LoginDTO dto);
}
