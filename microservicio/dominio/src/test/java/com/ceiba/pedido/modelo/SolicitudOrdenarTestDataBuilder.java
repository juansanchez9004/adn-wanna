package com.ceiba.pedido.modelo;

import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;
import com.ceiba.pedido.modelo.entidad.PuntoEntrega;
import com.ceiba.pedido.modelo.entidad.SolicitudOrdenar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolicitudOrdenarTestDataBuilder {

    private Cliente cliente;

    private Date fecha;

    private PuntoEntrega puntoEntrega;
    private List<ProductoOrdenado> productosOrdenados;

    public SolicitudOrdenarTestDataBuilder() {
        this.productosOrdenados = new ArrayList<>();
    }

    public SolicitudOrdenarTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public SolicitudOrdenarTestDataBuilder conFecha(Date fecha) {
        this.fecha = fecha;
        return this;
    }

    public SolicitudOrdenarTestDataBuilder conPuntoEntrega(PuntoEntrega puntoEntrega) {
        this.puntoEntrega = puntoEntrega;
        return this;
    }

    public SolicitudOrdenarTestDataBuilder conProductoOrdenado(ProductoOrdenado productoOrdenado) {
        this.productosOrdenados.add(productoOrdenado);
        return this;
    }

    public SolicitudOrdenarTestDataBuilder conProductosOrdenados(List<ProductoOrdenado> productosOrdenados) {
        this.productosOrdenados = productosOrdenados;
        return this;
    }

    public SolicitudOrdenar build() {
        return new SolicitudOrdenar(cliente, fecha, puntoEntrega, productosOrdenados);
    }
}
