package delivery.management.system.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "_order")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String place;
    private BigDecimal totalAmount;
    @OneToOne(cascade = CascadeType.PERSIST)
    private TableDetails details;
    @ManyToOne
    private Cart cart;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems;
    @ManyToOne
    private Driver driver;
    @ManyToOne
    private OrderStatus orderStatus;

    private LocalDateTime deliveryTime;



}
