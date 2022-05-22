package com.ceiba.producto;

import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.producto.modelo.entidad.TipoProducto;

import java.math.BigDecimal;

public class ProductoTestDataBuilder {

    private Long id;

    private String nombre;

    private TipoProducto tipoProducto;

    private BigDecimal valor;

    public ProductoTestDataBuilder conProductoPorDefecto(){
        this.id = 2l;
        this.nombre = "Producto 1";
        this.tipoProducto = TipoProducto.RELOJ;
        this.valor = BigDecimal.valueOf(559000);
        return this;
    }

    public Producto reconstruir() {
        return Producto.reconstruir(id, nombre, tipoProducto, valor);
    }

    public ProductoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ProductoTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoTestDataBuilder conTipoProducto(TipoProducto tipoProducto){
        this.tipoProducto = tipoProducto;
        return this;
    }

    public ProductoTestDataBuilder conValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }
}
