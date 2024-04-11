package delivery.management.system.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;
    @Builder.Default
    private boolean busy = false;
    @ManyToOne
    private DriverType driverType;
    @ManyToOne
    private Order order;
    private String carNumber; // FIXME admin panel
}
