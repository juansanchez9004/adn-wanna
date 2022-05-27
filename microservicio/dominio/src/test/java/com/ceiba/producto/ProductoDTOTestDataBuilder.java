package com.ceiba.producto;

import com.ceiba.producto.modelo.dto.ProductoDTO;
import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.producto.modelo.entidad.TipoProducto;

import java.math.BigDecimal;

public class ProductoDTOTestDataBuilder {

    private Long id;

    private String nombre;

    private String tipoProducto;

    private BigDecimal valor;

    public ProductoDTOTestDataBuilder conProductoDTOPorDefecto(){
        this.id = 2l;
        this.nombre = "Producto 1";
        this.tipoProducto = "RELOJ";
        this.valor = BigDecimal.valueOf(559000);
        return this;
    }

    public ProductoDTO reconstruir() {
        return new ProductoDTO(id, nombre, tipoProducto, valor);
    }

    public ProductoDTOTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ProductoDTOTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoDTOTestDataBuilder conTipoProducto(String tipoProducto){
        this.tipoProducto = tipoProducto;
        return this;
    }

    public ProductoDTOTestDataBuilder conValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }
}
