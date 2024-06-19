package hackeru.fridarik.snipsnapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String userId;
    @NotNull
    @Column(length = 1024)
    private String message;
    @NotNull
    private LocalDateTime timeStamp;
}
