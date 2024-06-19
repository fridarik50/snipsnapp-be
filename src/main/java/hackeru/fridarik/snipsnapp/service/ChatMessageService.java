package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.ChatMessageCreateDTO;
import hackeru.fridarik.snipsnapp.dto.ChatMessageListDTO;

public interface ChatMessageService {

    void saveMessage(ChatMessageCreateDTO chatMessageCreateDTO);

    ChatMessageListDTO getChatHistory(int pageNo, int pageSize, String sortDir, String... sortBy);
}
