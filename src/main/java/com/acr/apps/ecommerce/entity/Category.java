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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private LocalDateTime createdAt;


}
