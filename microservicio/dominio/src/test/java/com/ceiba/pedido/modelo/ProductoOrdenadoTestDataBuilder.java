package com.ceiba.pedido.modelo;

import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;
import com.ceiba.producto.entidad.Producto;

import java.math.BigDecimal;

public class ProductoOrdenadoTestDataBuilder {

    private Long id;

    private Producto producto;

    private Integer cantidad;

    private BigDecimal valorTotal;

    public ProductoOrdenadoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ProductoOrdenadoTestDataBuilder conProducto(Producto producto) {
        this.producto = producto;
        return this;
    }

    public ProductoOrdenadoTestDataBuilder conCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public ProductoOrdenadoTestDataBuilder conValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public ProductoOrdenado build() {
        return ProductoOrdenado.crear(producto, cantidad);
    }

    public ProductoOrdenado reconstruir() {
        return ProductoOrdenado.reconstruir(id, producto, cantidad, valorTotal);
    }
}
