package hackeru.fridarik.snipsnapp.service;

import hackeru.fridarik.snipsnapp.dto.ChatMessageCreateDTO;
import hackeru.fridarik.snipsnapp.dto.ChatMessageListDTO;
import hackeru.fridarik.snipsnapp.dto.ChatMessageResponseDTO;
import hackeru.fridarik.snipsnapp.entity.ChatMessage;
import hackeru.fridarik.snipsnapp.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public void saveMessage(ChatMessageCreateDTO chatMessageCreateDTO) {
        ChatMessage chatMessage = ChatMessage.builder()
                .userId(chatMessageCreateDTO.getUserId())
                .message(chatMessageCreateDTO.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        chatMessageRepository.save(chatMessage);
    }

    @Override
    public ChatMessageListDTO getChatHistory(int pageNo, int pageSize, String sortDir, String... sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        Page<ChatMessage> chatMessages = chatMessageRepository.findAll(pageable);

        List<ChatMessageResponseDTO> messageDTOs = chatMessages.getContent().stream()
                .map(chatMessage -> ChatMessageResponseDTO.builder()
                        .id(chatMessage.getId())
                        .userId(chatMessage.getUserId())
                        .message(chatMessage.getMessage())
                        .timestamp(chatMessage.getTimeStamp())
                        .build())
                .collect(Collectors.toList());

        return ChatMessageListDTO.builder()
                .totalMessages(chatMessages.getTotalElements())
                .pageNo(chatMessages.getNumber())
                .pageSize(chatMessages.getSize())
                .totalPages(chatMessages.getTotalPages())
                .isFirst(chatMessages.isFirst())
                .isLast(chatMessages.isLast())
                .messages(messageDTOs)
                .build();
    }
}
