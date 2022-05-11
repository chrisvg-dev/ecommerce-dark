package com.acr.apps.ecommerce.entity;

import com.acr.apps.ecommerce.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detail;
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private LocalDateTime createdAt;
}
