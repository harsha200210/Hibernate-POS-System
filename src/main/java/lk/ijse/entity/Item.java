package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    private int itemId;
    private String model;
    private double unitPrice;
    private int qty;

    @OneToMany(mappedBy = "item",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
