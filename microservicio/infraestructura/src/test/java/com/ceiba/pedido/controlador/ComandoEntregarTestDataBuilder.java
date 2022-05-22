package com.ceiba.pedido.controlador;

import com.ceiba.pedido.comando.ComandoEntregar;

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
