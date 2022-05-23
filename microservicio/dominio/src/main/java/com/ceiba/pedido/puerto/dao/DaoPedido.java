package com.ceiba.pedido.puerto.dao;

import com.ceiba.pedido.modelo.dto.ResumenPedidoDTO;

import java.util.List;

public interface DaoPedido {

    List<ResumenPedidoDTO> obtenerResumenDePedidosEntregados();

    List<ResumenPedidoDTO> obtenerResumenDePedidosPendientes();
}
