package com.ceiba.pedido.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.producto.modelo.entidad.Producto;

import java.math.BigDecimal;

public final class ProductoOrdenado {

    private Long id;

    private final Producto producto;

    private final Integer cantidad;

    private final BigDecimal valorTotal;

    private ProductoOrdenado(Long id, Producto producto, Integer cantidad, BigDecimal valorTotal) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
    }

    private ProductoOrdenado(Producto producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.valorTotal = calcularValorTotal(producto, cantidad);
    }

    public static ProductoOrdenado crear(Producto producto, Integer cantidad) {
        ValidadorArgumento.validarObligatorio(producto, "Producto es requerido");
        ValidadorArgumento.validarObligatorio(cantidad, "Cantidad es requerida");
        return new ProductoOrdenado(producto, cantidad);
    }

    public static ProductoOrdenado reconstruir(Long id, Producto producto, Integer cantidad, BigDecimal valorTotal) {
        ValidadorArgumento.validarObligatorio(producto, "Producto es requerido");
        ValidadorArgumento.validarObligatorio(cantidad, "Cantidad es requerida");
        ValidadorArgumento.validarObligatorio(valorTotal, "Valor Total es requerida");
        return new ProductoOrdenado(id, producto, cantidad, valorTotal);
    }

    public BigDecimal calcularValorTotal(Producto producto, Integer cantidad) {
        return producto.getValor().multiply(BigDecimal.valueOf(cantidad));
    }

    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
