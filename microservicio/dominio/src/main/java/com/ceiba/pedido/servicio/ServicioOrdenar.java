package com.ceiba.pedido.servicio;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.modelo.entidad.SolicitudOrdenar;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioOrdenar {

    private final RepositorioPedido repositorioPedido;

    public ServicioOrdenar(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public Long ejecutar(SolicitudOrdenar solicitudOrdenar) {
        var pedido = Pedido.crear(solicitudOrdenar);
        return repositorioPedido.guardar(pedido);
    }
}
