package com.acr.apps.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float total;
    private Float subTotal;
    private Float iva;
    private String transactionId;
    private String payDate;
    private LocalDate saleDate;
    private LocalDateTime createdAt;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sale_product",
            joinColumns = {@JoinColumn(name = "fk_saleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fk_productId", referencedColumnName = "id") })
    private List<Product> products;
}
