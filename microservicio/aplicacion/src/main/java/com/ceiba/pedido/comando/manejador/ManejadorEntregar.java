package com.ceiba.comando.manejador;

import com.ceiba.comando.ComandoEntregar;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioEntregar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEntregar implements ManejadorComando<ComandoEntregar> {

    private final ServicioEntregar servicioEntregar;

    private final RepositorioPedido repositorioPedido;

    public ManejadorEntregar(ServicioEntregar servicioEntregar, RepositorioPedido repositorioPedido) {
        this.servicioEntregar = servicioEntregar;
        this.repositorioPedido = repositorioPedido;
    }

    @Override
    public void ejecutar(ComandoEntregar comandoEntregar) {
        servicioEntregar.ejecutar(repositorioPedido.obtener(comandoEntregar.getIdPedido()));
    }
}
