package hackeru.fridarik.snipsnapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageResponseDTO {

    private Long id;
    private String userId;
    private String message;
    private LocalDateTime timestamp;
}