package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.OrderBO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderItem;
import lk.ijse.entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderBOImpl implements OrderBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAO(DAOFactory.DAOType.ITEM);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public ItemDTO getItem(int itemId) {
        Item item = itemDAO.getItem(itemId);
        return new ItemDTO(item.getItemId(),item.getModel(),item.getUnitPrice(),item.getQty(),item.getOrderItems());
    }

    @Override
    public CustomerDTO getCustomer(int customerId) {
        Customer customer = customerDAO.getCustomer(customerId);
        return new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getTel(),customer.getOrders());
    }

    @Override
    public void placeOrder(OrderDTO orderDTO,Customer customer) {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Orders order = new Orders(orderDTO.getOrderId(),orderDTO.getDate(),orderDTO.getTotal(),orderDTO.getCustomer(),orderDTO.getOrderItems());
        customer.getOrders().add(order);

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
            orderItem.getItem().setOrderItems(order.getOrderItems());
        }

        try {
            session.save(order);
            session.update(customer);

            for (OrderItem orderItem : order.getOrderItems()) {
                session.save(orderItem);
                session.update(orderItem.getItem());
                Query query = session.createQuery("update Item set qty = qty - :qty where itemId = :id");
                query.setParameter("qty",orderItem.getQty());
                query.setParameter("id",orderItem.getItem().getItemId());
                query.executeUpdate();
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
