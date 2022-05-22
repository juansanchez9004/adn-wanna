package com.ceiba.pedido.modelo.entidad;

import com.ceiba.cliente.modelo.entidad.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class SolicitudOrdenar {

    private final Cliente cliente;
    private final LocalDate fecha;
    private final PuntoEntrega puntoEntrega;
    private final List<ProductoOrdenado> productosOrdenados;
}
