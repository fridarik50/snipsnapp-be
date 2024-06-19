package hackeru.fridarik.snipsnapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Barber implements AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(length = 512)
    private String skills;

    @NotNull
    @Column(length = 512)
    private String experience;

    @NotNull
    private String workingHours;

    @NotNull
    private String address;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
