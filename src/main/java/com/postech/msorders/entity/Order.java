package com.postech.msorders.entity;


import com.postech.msorders.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_Order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID idCustomer;

    private LocalDateTime orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;

    public Order(OrderDTO orderDTO) {
        this.id = UUID.randomUUID();
        this.idCustomer = UUID.fromString(orderDTO.getIdCustomer());
        this.itens = orderDTO.getItens();
        this.orderDate = LocalDateTime.now();
    }
}
