package com.ceiba.pedido.modelo.entidad;

import com.ceiba.cliente.entidad.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class SolicitudOrdenar {

    private final Cliente cliente;
    private final Date fecha;
    private final PuntoEntrega puntoEntrega;
    private final List<ProductoOrdenado> productosOrdenados;
}
