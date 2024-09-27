package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Order_Item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for the primary key
    private int id;

    @ManyToOne
    @JoinColumn(name = "Order_Id", foreignKey = @ForeignKey(name = "FK_ORDER"))
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "Item_Id", foreignKey = @ForeignKey(name = "FK_ITEM"))
    private Item item;

    @Column(name = "qty", nullable = false)
    private int qty;

    public OrderItem(Item item, int qty) {
        this.item = item;
        this.qty = qty;
    }
}

