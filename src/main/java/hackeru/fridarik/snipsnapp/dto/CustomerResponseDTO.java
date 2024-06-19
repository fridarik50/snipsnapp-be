package hackeru.fridarik.snipsnapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDTO {

        private Long id;
        private String name;
        private String phone;
        private String email;
        private String password;
        private final String role = "CUSTOMER";
}
