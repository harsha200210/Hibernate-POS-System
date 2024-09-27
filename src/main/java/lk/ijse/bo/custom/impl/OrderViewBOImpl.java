package lk.ijse.bo.custom.impl;

import lk.ijse.Launcher;
import lk.ijse.bo.custom.OrderViewBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.OrderItemDAO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.dto.OrderItemDTO;
import lk.ijse.entity.OrderItem;
import lk.ijse.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrderViewBOImpl implements OrderViewBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAO(DAOFactory.DAOType.ORDER);
    OrderItemDAO orderItemDAO = (OrderItemDAO) DAOFactory.getDAO(DAOFactory.DAOType.ORDERITEM);

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO>  orderDTOS = new ArrayList<>();
        List<Orders> all = orderDAO.findAll();
        for (Orders order : all) {
            orderDTOS.add(new OrderDTO(order.getOrderId(),order.getDate(),order.getTotal(),order.getCustomer(),order.getOrderItems()));
        }
        return orderDTOS;
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrderId(int orderId) {
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        List<OrderItem> byOrderId = orderItemDAO.getByOrderId(orderId);
        for (OrderItem orderItem : byOrderId) {
            orderItemDTOS.add(new OrderItemDTO(orderItem.getId(),orderItem.getOrder(),orderItem.getItem(),orderItem.getQty()));
        }
        return orderItemDTOS;
    }
}
