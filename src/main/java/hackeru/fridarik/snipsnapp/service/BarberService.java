package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.BarberCreateDTO;
import hackeru.fridarik.snipsnapp.dto.BarberResponseDTO;
import hackeru.fridarik.snipsnapp.dto.EntityListDTO;
import hackeru.fridarik.snipsnapp.entity.Barber;

import java.util.List;

public interface BarberService {

    BarberResponseDTO createBarber(BarberCreateDTO dto);

    EntityListDTO<BarberResponseDTO> getAllBarbers(int pageNo, int pageSize, String sortDir, String... sortBy);

    BarberResponseDTO getBarberById(long id);

    BarberResponseDTO updateBarberInfo(long id, BarberCreateDTO dto);

    BarberResponseDTO deleteBarber(long id);

    Barber getBarberEntityOrThrow(long id);
    List<BarberResponseDTO> searchByName(String name);
}
