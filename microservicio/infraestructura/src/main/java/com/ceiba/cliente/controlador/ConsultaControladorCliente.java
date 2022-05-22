package com.ceiba.cliente.controlador;

import com.ceiba.cliente.consulta.ManejadorConsultarClientesTodos;
import com.ceiba.cliente.modelo.dto.ClienteDTO;
import com.ceiba.pedido.modelo.dto.ResumenPedidoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Controlador consulta cliente")
public class ConsultaControladorCliente {

    private final ManejadorConsultarClientesTodos manejadorConsultarClientesTodos;

    public ConsultaControladorCliente(ManejadorConsultarClientesTodos manejadorConsultarClientesTodos) {
        this.manejadorConsultarClientesTodos = manejadorConsultarClientesTodos;
    }

    @GetMapping("todos")
    @Operation(summary = "Todos", description = "Metodo utilizado para consultar todos los clientes")
    public List<ClienteDTO> obtenerTodos() {
        return manejadorConsultarClientesTodos.ejecutar();
    }
}
