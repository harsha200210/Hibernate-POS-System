package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {

    void save(ItemDTO itemDTO);

    void update(ItemDTO itemDTO);

    void delete(ItemDTO itemDTO);

    List<ItemDTO> getAllItems();

}
