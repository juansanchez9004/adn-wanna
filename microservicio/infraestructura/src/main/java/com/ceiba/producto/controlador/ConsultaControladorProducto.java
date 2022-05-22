package com.ceiba.producto.controlador;

import com.ceiba.producto.consulta.ManejadorConsultarProductosTodos;
import com.ceiba.producto.modelo.dto.ProductoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producto")
@Tag(name = "Controlador consulta productos")
public class ConsultaControladorProducto {

    private final ManejadorConsultarProductosTodos manejadorConsultarProductosTodos;

    public ConsultaControladorProducto(ManejadorConsultarProductosTodos manejadorConsultarProductosTodos) {
        this.manejadorConsultarProductosTodos = manejadorConsultarProductosTodos;
    }

    @GetMapping("todos")
    @Operation(summary = "Todos", description = "Metodo utilizado para consultar todos los productos")
    public List<ProductoDTO> obtenerTodos() {
        return manejadorConsultarProductosTodos.ejecutar();
    }
}
