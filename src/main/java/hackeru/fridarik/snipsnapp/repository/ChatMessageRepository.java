package hackeru.fridarik.snipsnapp.repository;

import hackeru.fridarik.snipsnapp.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}