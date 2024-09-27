package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void saveCustomer(Customer customer) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer);

        transaction.commit();
        session.close();
    }

    @Override
    public void updateCustomer(Customer customer) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(customer);

        transaction.commit();
        session.close();
    }

    @Override
    public void deleteCustomer(Customer customer) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.delete(customer);

        transaction.commit();
        session.close();
    }

    @Override
    public Customer getCustomer(int customerId){
        Customer customer = null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        customer = session.get(Customer.class, customerId);

        transaction.commit();
        session.close();

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers(){
        List<Customer> customers = null;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        customers = session.createQuery("from Customer", Customer.class).list();

        transaction.commit();
        session.close();

        return customers;
    }
}
