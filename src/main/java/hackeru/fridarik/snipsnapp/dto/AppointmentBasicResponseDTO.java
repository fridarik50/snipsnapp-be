package hackeru.fridarik.snipsnapp.dto;

import hackeru.fridarik.snipsnapp.entity.Barber;
import hackeru.fridarik.snipsnapp.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AppointmentBasicResponseDTO {
    private Long id;
    private LocalDate date;
    private LocalTime startHour;
    private LocalTime endingHour;
    private String comments;
}
