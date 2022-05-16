package com.ceiba.pedido.puerto.repositorio;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;

import java.util.List;

public interface RepositorioProductoOrdenado {

    void guardarPorPedido(Pedido pedido, Long idPedido);

    List<ProductoOrdenado> obtenerPorPedido(Long idPedido);
}
