package com.ceiba.consulta;

import com.ceiba.pedido.modelo.dto.ResumenPedidoDTO;
import com.ceiba.pedido.puerto.dao.DaoPedido;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarPedidosEntregados {

    private final DaoPedido daoPedido;

    public ManejadorConsultarPedidosEntregados(DaoPedido daoPedido) {
        this.daoPedido = daoPedido;
    }

    public List<ResumenPedidoDTO> ejecutar() {
        return daoPedido.obtenerResumenDePedidosEntregados();
    }
}
