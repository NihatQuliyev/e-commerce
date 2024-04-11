package delivery.management.system.model.entity;

import delivery.management.system.model.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
    private LocalDateTime creatAt;
    private boolean confirm;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

}
