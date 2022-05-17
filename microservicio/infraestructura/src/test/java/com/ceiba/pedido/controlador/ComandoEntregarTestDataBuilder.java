package com.ceiba.pedido.controlador;

import com.ceiba.comando.ComandoEntregar;
import com.ceiba.comando.ComandoProductoOrdenar;
import com.ceiba.comando.ComandoPuntoEntrega;
import com.ceiba.comando.ComandoSolicitudOrdenar;

public class ComandoEntregarTestDataBuilder {

    private Long idPedido;

    public ComandoEntregarTestDataBuilder crearPorDefecto() {
        this.idPedido = 2l;
        return this;
    }

    public ComandoEntregar build() {
        return new ComandoEntregar(this.idPedido);
    }
}
