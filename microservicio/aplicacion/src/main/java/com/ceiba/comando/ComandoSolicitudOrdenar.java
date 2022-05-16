package com.ceiba.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoSolicitudOrdenar {

    private Long idCliente;

    private Date fecha;

    private ComandoPuntoEntrega comandoPuntoEntrega;

    private List<ComandoProductoOrdenar> comandoProductosOrdenados;
}
