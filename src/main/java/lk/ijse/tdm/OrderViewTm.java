package lk.ijse.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderViewTm {
    private int orderId;
    private Date date;
    private double total;
    private int customerId;
    private String customerName;
}
