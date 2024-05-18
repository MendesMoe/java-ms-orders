package com.postech.msorders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_Item")
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID idProduct;

    private BigDecimal quantity;

    public Item(UUID i, long j) {
        this.id = 1L;
        this.idProduct = i;
        this.quantity = BigDecimal.valueOf(j);
    }

}
