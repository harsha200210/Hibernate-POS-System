package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Item;

import java.util.List;

public interface ItemDAO extends SuperDAO {

    void saveItem(Item item);

    void updateItem(Item item);

    void deleteItem(Item item);

    Item getItem(int itemId);

    List<Item> getAllItems();
}
