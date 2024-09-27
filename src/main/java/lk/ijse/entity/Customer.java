package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    private int customerId;
    private String name;
    private String address;
    private int tel;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Orders> orders;
}
