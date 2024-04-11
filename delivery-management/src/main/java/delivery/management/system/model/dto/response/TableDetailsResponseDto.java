package delivery.management.system.model.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableDetailsResponseDto {

    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;
}
