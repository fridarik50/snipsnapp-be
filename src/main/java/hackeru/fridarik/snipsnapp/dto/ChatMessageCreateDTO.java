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
public class ChatMessageCreateDTO {

    @NotNull
    @Size(min = 2, max = 128)
    private String userId;

    @NotNull
    @Size(min = 1, max = 1024)
    private String message;
}
