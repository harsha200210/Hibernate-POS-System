package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Customer;

import java.util.List;

public interface CustomerDAO extends SuperDAO {

    void saveCustomer(Customer customer) throws Exception;

    void deleteCustomer(Customer customer);

    void updateCustomer(Customer customer);

    Customer getCustomer(int customerId);

    List<Customer> getAllCustomers();
}
