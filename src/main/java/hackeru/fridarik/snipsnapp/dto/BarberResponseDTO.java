package hackeru.fridarik.snipsnapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BarberResponseDTO {

    private Long id;
    private String name;
    private String skills;
    private String experience;
    private String workingHours;
    private String address;
    private String phone;
    private String email;
    private final String role = "BARBER";
}
