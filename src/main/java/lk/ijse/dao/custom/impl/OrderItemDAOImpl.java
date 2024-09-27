package lk.ijse.dao.custom.impl;

import jakarta.persistence.Query;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.OrderItemDAO;
import lk.ijse.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public List<OrderItem> getByOrderId(int orderId) {
        List<OrderItem> orderItems = null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from OrderItem where order.orderId = :id");
        query.setParameter("id", orderId);
        orderItems = query.getResultList();

        transaction.commit();
        session.close();

        return orderItems;
    }
}
