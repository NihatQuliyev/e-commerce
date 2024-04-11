package delivery.management.system.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int count;
    private BigDecimal totalAmount;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;
}
