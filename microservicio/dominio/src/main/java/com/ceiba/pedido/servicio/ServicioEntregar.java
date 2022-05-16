package com.ceiba.pedido.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioEntregar {

    private final RepositorioPedido repositorioPedido;

    public ServicioEntregar(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public void ejecutar(Pedido pedido) {
        ValidadorArgumento.validarObligatorio(pedido, "No existe un pedido para entregar");
        pedido.entregar();
        repositorioPedido.actualizarEstado(pedido);
    }
}