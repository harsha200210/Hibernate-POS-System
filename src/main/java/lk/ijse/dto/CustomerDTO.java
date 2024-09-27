package lk.ijse.dto;

import lk.ijse.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private int customerId;
    private String name;
    private String address;
    private int tel;
    private List<Orders> orders;
}
