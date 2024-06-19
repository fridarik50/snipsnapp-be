package hackeru.fridarik.snipsnapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerCreateDTO {

    @NotNull
    @Size(min = 2, max = 128)
    private String name;

    @NotNull
    @Size(min = 2, max = 128)
    private String phone;

    @NotNull
    @Size(min = 2, max = 128)
    private String email;

    @NotNull
    @Size(min = 4, max = 16)
    private String password;
}
