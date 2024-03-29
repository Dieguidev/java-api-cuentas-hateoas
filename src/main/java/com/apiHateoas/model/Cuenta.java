package com.apiHateoas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "cuentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta extends RepresentationModel<Cuenta> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_cuenta", length = 20, nullable = false, unique = true)
    private String numeroCuenta;

    private float monto;

    public Cuenta(Integer id, String numeroCuenta) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
    }
}
