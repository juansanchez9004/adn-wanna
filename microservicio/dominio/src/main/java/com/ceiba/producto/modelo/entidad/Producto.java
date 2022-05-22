package com.ceiba.producto.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

import java.math.BigDecimal;

public final class Producto {

    private Long id;

    private final String nombre;

    private final TipoProducto tipoProducto;

    private final BigDecimal valor;

    private Producto(Long id, String nombre, TipoProducto tipoProducto, BigDecimal valor) {
        this.id = id;
        this.nombre = nombre;
        this.tipoProducto = tipoProducto;
        this.valor = valor;
    }

    public static Producto reconstruir(Long id, String nombre, TipoProducto tipoProducto, BigDecimal valor) {
        ValidadorArgumento.validarObligatorio(id, "Id del producto es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "Nombre del producto es requerido");
        ValidadorArgumento.validarObligatorio(tipoProducto, "Tipo Producto es requerido");
        ValidadorArgumento.validarObligatorio(valor, "El valor del producto es requerido");
        return new Producto(id, nombre, tipoProducto, valor);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public boolean esTipoPermufe() {
        return this.tipoProducto.equals(TipoProducto.PERFUME);
    }

    public boolean esTipoReloj() {
        return this.tipoProducto.equals(TipoProducto.RELOJ);
    }

    public boolean esTipoCosmetico() {
        return this.tipoProducto.equals(TipoProducto.COSMETICO);
    }
}
