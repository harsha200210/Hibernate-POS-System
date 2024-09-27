package lk.ijse.bo;

import lk.ijse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.bo.custom.impl.ItemBOImpl;
import lk.ijse.bo.custom.impl.OrderBOImpl;
import lk.ijse.bo.custom.impl.OrderViewBOImpl;

public class BOFactory {

    public enum BOType{
        CUSTOMER,ITEM,ORDER,ORDERVIEW
    }

    public static SuperBO getBO(BOType boType){
        return switch (boType) {
            case CUSTOMER -> new CustomerBOImpl();
            case ITEM -> new ItemBOImpl();
            case ORDER -> new OrderBOImpl();
            case ORDERVIEW -> new OrderViewBOImpl();
            default -> null;
        };
    }
}
