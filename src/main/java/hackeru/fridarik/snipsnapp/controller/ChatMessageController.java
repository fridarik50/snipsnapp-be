package hackeru.fridarik.snipsnapp.controller;

import hackeru.fridarik.snipsnapp.dto.ChatMessageCreateDTO;
import hackeru.fridarik.snipsnapp.dto.ChatMessageListDTO;
import hackeru.fridarik.snipsnapp.service.ChatMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/api/v1/chat/messages")
@RequiredArgsConstructor
@Validated
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    /*
    @PostMapping
    public ResponseEntity<Void> saveMessage(@RequestBody @Valid ChatMessageCreateDTO chatMessageCreateDTO) {
        chatMessageService.saveMessage(chatMessageCreateDTO);
        return ResponseEntity.ok().build();
    }
    */

    @MessageMapping("/incoming")
    @SendTo("/topic/outgoing")
    public  String chat(String message){
        return "got " + message;
    }

    //@MessageMapping("/socket.io")
    public ResponseEntity<ChatMessageListDTO> getChatHistory(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir,
            @RequestParam(value = "sortBy", required = false, defaultValue = "timestamp") String... sortBy
    ) {
        ChatMessageListDTO chatHistory = chatMessageService.getChatHistory(pageNo, pageSize, sortDir, sortBy);
        return ResponseEntity.ok(chatHistory);
    }

}
