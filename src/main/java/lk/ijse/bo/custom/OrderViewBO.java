package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderItemDTO;
import lk.ijse.entity.Orders;

import java.util.List;

public interface OrderViewBO extends SuperBO {

    List<OrderDTO> getAllOrders();

    List<OrderItemDTO> getOrderItemsByOrderId(int orderId);
}
