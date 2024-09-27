package lk.ijse.dto;

import lk.ijse.entity.OrderItem;
import lk.ijse.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private int itemId;
    private String model;
    private double unitPrice;
    private int qty;
    private List<OrderItem> orderItems;
}
