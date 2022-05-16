package com.ceiba.pedido.controlador;

import com.ceiba.consulta.ManejadorConsultarPedidosEntregados;
import com.ceiba.pedido.modelo.dto.ResumenPedidoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Controlador consulta pedido")
public class ConsultaControladorPedido {

    private final ManejadorConsultarPedidosEntregados manejadorConsultarPedidosEntregados;

    public ConsultaControladorPedido(ManejadorConsultarPedidosEntregados manejadorConsultarPedidosEntregados) {
        this.manejadorConsultarPedidosEntregados = manejadorConsultarPedidosEntregados;
    }

    @GetMapping("entregados")
    @Operation(summary = "Entragados", description = "Metodo utilizado para consultar los pedidos entregados")
    public List<ResumenPedidoDTO> obtenerEntregados() {
        return manejadorConsultarPedidosEntregados.ejecutar();
    }
}
