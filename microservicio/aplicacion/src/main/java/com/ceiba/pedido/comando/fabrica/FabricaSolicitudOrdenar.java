package com.ceiba.comando.fabrica;

import com.ceiba.cliente.puerto.repositorio.RepositorioCliente;
import com.ceiba.comando.ComandoProductoOrdenar;
import com.ceiba.comando.ComandoPuntoEntrega;
import com.ceiba.comando.ComandoSolicitudOrdenar;
import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;
import com.ceiba.pedido.modelo.entidad.PuntoEntrega;
import com.ceiba.pedido.modelo.entidad.SolicitudOrdenar;
import com.ceiba.producto.puerto.repositorio.RepositorioProducto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FabricaSolicitudOrdenar {

    private final RepositorioCliente repositorioCliente;

    private final RepositorioProducto repositorioProducto;

    public FabricaSolicitudOrdenar(RepositorioCliente repositorioCliente, RepositorioProducto repositorioProducto) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioProducto = repositorioProducto;
    }

    public SolicitudOrdenar crear(ComandoSolicitudOrdenar comandoSolicitudOrdenar) {
        return new SolicitudOrdenar(repositorioCliente.obtener(comandoSolicitudOrdenar.getIdCliente()),
                comandoSolicitudOrdenar.getFecha(),
                obtenerPuntoEntrega(comandoSolicitudOrdenar.getComandoPuntoEntrega()),
                obtenerProductos(comandoSolicitudOrdenar.getComandoProductosOrdenados()));
    }

    private List<ProductoOrdenado> obtenerProductos(List<ComandoProductoOrdenar> comandoProductosOrdenar) {
        return comandoProductosOrdenar.stream().map(comandoProducto ->
                    ProductoOrdenado.crear(repositorioProducto.obtener(comandoProducto.getIdProducto()),
                            comandoProducto.getCantidad())
                ).toList();
    }

    private PuntoEntrega obtenerPuntoEntrega(ComandoPuntoEntrega comandoPuntoEntrega) {
        return PuntoEntrega.crear(comandoPuntoEntrega.getDireccion(), comandoPuntoEntrega.getMunicipio());
    }
}
