package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.ItemDAOImpl;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.dao.custom.impl.OrderItemDAOImpl;

public class DAOFactory {

    public enum DAOType{
        CUSTOMER,ITEM,ORDER,ORDERITEM
    }

    public static SuperDAO getDAO(DAOType daoType){
        return switch (daoType) {
            case CUSTOMER -> new CustomerDAOImpl();
            case ITEM -> new ItemDAOImpl();
            case ORDER -> new OrderDAOImpl();
            case ORDERITEM -> new OrderItemDAOImpl();
            default -> null;
        };
    }
}
