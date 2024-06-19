package hackeru.fridarik.snipsnapp.dto;

import hackeru.fridarik.snipsnapp.entity.Barber;
import hackeru.fridarik.snipsnapp.entity.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentCreateDTO {

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startHour;

    @NotNull
    private LocalTime endingHour;

    @Size(min = 0, max = 512)
    private String comments;

    @NotNull
    private BarberResponseDTO barber;

    @NotNull
    private CustomerResponseDTO customer;

}
