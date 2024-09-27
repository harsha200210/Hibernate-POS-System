package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.dto.OrderDTO;
import lk.ijse.entity.Customer;

public interface OrderBO extends SuperBO {

    ItemDTO getItem(int itemId);

    CustomerDTO getCustomer(int customerId);

    void placeOrder(OrderDTO orderDTO, Customer customer);
}
