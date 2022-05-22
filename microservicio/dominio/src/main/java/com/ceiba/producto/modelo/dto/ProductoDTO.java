package com.ceiba.producto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String tipoProducto;
    private BigDecimal valor;
}
