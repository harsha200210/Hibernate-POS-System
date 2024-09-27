package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public List<Orders> findAll() {
        List<Orders> list = null;

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        list = session.createQuery("from Orders").list();

        transaction.commit();
        session.close();

        return list;
    }
}
