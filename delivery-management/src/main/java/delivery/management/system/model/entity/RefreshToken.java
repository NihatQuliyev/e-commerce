package delivery.management.system.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long lifeTime;
    private boolean revoked;
    @OneToOne
    private TableDetails tableDetails;
    @OneToOne
    private User user;
}
