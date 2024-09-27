package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Orders;

import java.util.List;

public interface OrderDAO extends SuperDAO {

    List<Orders> findAll();
}
