package delivery.management.system.model.entity;

import delivery.management.system.repository.CartItemRepository;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Builder.Default
    private int count = 0;
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

}