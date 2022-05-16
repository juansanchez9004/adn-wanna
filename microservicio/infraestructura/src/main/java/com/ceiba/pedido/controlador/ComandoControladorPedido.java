package com.ceiba.pedido.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.comando.ComandoEntregar;
import com.ceiba.comando.ComandoSolicitudOrdenar;
import com.ceiba.comando.manejador.ManejadorEntregar;
import com.ceiba.comando.manejador.ManejadorOrdenar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Controlador comando pedido")
public class ComandoControladorPedido {

    private final ManejadorOrdenar manejadorOrdenar;

    private final ManejadorEntregar manejadorEntregar;

    public ComandoControladorPedido(ManejadorOrdenar manejadorOrdenar, ManejadorEntregar manejadorEntregar) {
        this.manejadorOrdenar = manejadorOrdenar;
        this.manejadorEntregar = manejadorEntregar;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Pedido", description = "Metodo utilizado para crear un nuevo pedido")
    public ComandoRespuesta<Long> ordenar(@RequestBody ComandoSolicitudOrdenar comandoSolicitudOrdenar) {
        return this.manejadorOrdenar.ejecutar(comandoSolicitudOrdenar);
    }

    @PostMapping("entregar/{id-pedido}")
    @Operation(summary = "Entregar", description = "Metodo utilizado para entregar un nuevo pedido")
    public void entregar(@PathVariable("id-pedido") Long idPedido) {
        this.manejadorEntregar.ejecutar(new ComandoEntregar(idPedido));
    }
}
