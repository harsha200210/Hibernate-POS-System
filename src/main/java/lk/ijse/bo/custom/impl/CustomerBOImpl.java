package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public void save(CustomerDTO customerDTO) throws Exception {
        customerDAO.saveCustomer(new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTel(),new ArrayList<>()));
    }

    @Override
    public void update(CustomerDTO customerDTO) {
        customerDAO.updateCustomer(new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTel(),new ArrayList<>()));
    }

    @Override
    public void delete(CustomerDTO customerDTO) {
        customerDAO.deleteCustomer(new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTel(),new ArrayList<>()));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerDAO.getAllCustomers();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : allCustomers) {
            customerDTOS.add(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getTel(), customer.getOrders()));
        }
        return customerDTOS;
    }
}
