package com.acr.apps.ecommerce.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String address;
    private LocalDate birthday;

    @ManyToMany
    @JoinTable (
            name = "rol_user",
            joinColumns = @JoinColumn( name = "fk_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "fk_rol", referencedColumnName = "id")
    )
    private Set<Rol> roles = new HashSet<>();

    private LocalDateTime createdAt;
}
