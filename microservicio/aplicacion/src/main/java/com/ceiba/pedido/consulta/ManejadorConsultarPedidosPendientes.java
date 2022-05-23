package com.ceiba.pedido.consulta;

import com.ceiba.pedido.modelo.dto.ResumenPedidoDTO;
import com.ceiba.pedido.puerto.dao.DaoPedido;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarPedidosPendientes {

    private final DaoPedido daoPedido;

    public ManejadorConsultarPedidosPendientes(DaoPedido daoPedido) {
        this.daoPedido = daoPedido;
    }

    public List<ResumenPedidoDTO> ejecutar() {
        return daoPedido.obtenerResumenDePedidosPendientes();
    }
}
