package hackeru.fridarik.snipsnapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startHour;

    @NotNull
    private LocalTime endingHour;

    @NotNull
    @Column(length = 512)
    private String comments;

    @ManyToOne()
    private Barber barber;

    @ManyToOne()
    private Customer customer;
}
