package com.acr.apps.ecommerce.security.entity;

import com.acr.apps.ecommerce.security.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private RoleEnum rol; // ROLE_SELLER, ROLE_BUYER, ROLE_ADMIN
    private LocalDate createdAt;
}