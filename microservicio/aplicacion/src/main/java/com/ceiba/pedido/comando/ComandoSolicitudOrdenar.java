package com.ceiba.pedido.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudOrdenar {

    private Long idCliente;

    private LocalDate fecha;

    private ComandoPuntoEntrega comandoPuntoEntrega;

    private List<ComandoProductoOrdenar> comandoProductosOrdenados;
}
