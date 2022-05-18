package com.ceiba.pedido.controlador;

import com.ceiba.comando.ComandoProductoOrdenar;
import com.ceiba.comando.ComandoPuntoEntrega;
import com.ceiba.comando.ComandoSolicitudOrdenar;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComandoSolicitudOrdenarTestDataBuilder {

    private Long idCliente;

    private Date fecha;

    private ComandoPuntoEntrega comandoPuntoEntrega;

    private List<ComandoProductoOrdenar> comandoProductosOrdenados;

    public ComandoSolicitudOrdenarTestDataBuilder() {
        this.comandoProductosOrdenados = new ArrayList<>();
    }

    public ComandoSolicitudOrdenarTestDataBuilder crearPorDefecto() {
        this.idCliente = 1l;
        this.fecha = Date.from(LocalDate.of(2022, 5, 17)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.comandoPuntoEntrega = new ComandoPuntoEntrega("Calle 5c - 2 sur # 345", "Medellin");
        this.comandoProductosOrdenados.add(new ComandoProductoOrdenar(1l, 3));
        this.comandoProductosOrdenados.add(new ComandoProductoOrdenar(2l, 2));
        return this;
    }

    public ComandoSolicitudOrdenar build() {
        return new ComandoSolicitudOrdenar(this.idCliente, this.fecha, comandoPuntoEntrega, comandoProductosOrdenados);
    }
}
