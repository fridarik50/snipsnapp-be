package hackeru.fridarik.snipsnapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageListDTO {

    private long totalMessages;
    private int pageNo;
    private int pageSize;
    private int totalPages;

    private boolean isFirst;
    private boolean isLast;

    private List<ChatMessageResponseDTO> messages;
}
