package com.ceiba.pedido.puerto.repositorio;

import com.ceiba.pedido.modelo.entidad.Pedido;

public interface RepositorioPedido {

    Long guardar(Pedido pedido);

    Pedido obtener(Long id);

    void actualizarEstado(Pedido pedido);
}
