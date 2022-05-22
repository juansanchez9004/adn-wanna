package com.ceiba.pedido.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.pedido.comando.ComandoSolicitudOrdenar;
import com.ceiba.pedido.comando.fabrica.FabricaSolicitudOrdenar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.pedido.servicio.ServicioOrdenar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorOrdenar implements ManejadorComandoRespuesta<ComandoSolicitudOrdenar, ComandoRespuesta<Long>> {

    private final FabricaSolicitudOrdenar fabricaSolicitudOrdenar;

    private final ServicioOrdenar servicioOrdenar;

    public ManejadorOrdenar(FabricaSolicitudOrdenar fabricaSolicitudOrdenar, ServicioOrdenar servicioOrdenar) {
        this.fabricaSolicitudOrdenar = fabricaSolicitudOrdenar;
        this.servicioOrdenar = servicioOrdenar;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitudOrdenar comandoSolicitudOrdenar) {
        return new ComandoRespuesta<>(servicioOrdenar
                .ejecutar(fabricaSolicitudOrdenar.crear(comandoSolicitudOrdenar)));
    }
}
