package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.OrderItem;

import java.util.List;

public interface OrderItemDAO extends SuperDAO {

    List<OrderItem> getByOrderId(int orderId);
}
