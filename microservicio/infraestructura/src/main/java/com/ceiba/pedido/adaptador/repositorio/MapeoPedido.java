package com.ceiba.pedido.adaptador.repositorio;

import com.ceiba.cliente.puerto.RepositorioCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pedido.modelo.entidad.EstadoPedido;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.modelo.entidad.PuntoEntrega;
import com.ceiba.pedido.puerto.repositorio.RepositorioProductoOrdenado;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoPedido implements RowMapper<Pedido>, MapperResult {

    private final RepositorioCliente repositorioCliente;

    private final RepositorioProductoOrdenado repositorioProductoOrdenado;

    public MapeoPedido(RepositorioCliente repositorioCliente, RepositorioProductoOrdenado repositorioProductoOrdenado) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioProductoOrdenado = repositorioProductoOrdenado;
    }

    @Override
    public Pedido mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var idCliente = resultSet.getLong("id_cliente");
        var fecha = resultSet.getDate("fecha");
        var puntoEntrega = resultSet.getString("punto_entrega");
        var valorTotal = resultSet.getBigDecimal("valor_Total");
        var estado = EstadoPedido.valueOf(resultSet.getString("estado"));

        return Pedido.reconstruir(id, fecha, repositorioCliente.obtener(idCliente),
                mapeoPuntoEntrega(puntoEntrega),
                repositorioProductoOrdenado.obtenerPorPedido(id),
                valorTotal, estado);
    }

    public PuntoEntrega mapeoPuntoEntrega(String puntoEntregaCompuesto) {
        String[] puntoEntregaDescompuesto =  puntoEntregaCompuesto.split(" - ");
        return PuntoEntrega.crear(puntoEntregaDescompuesto[0], puntoEntregaDescompuesto[1]);
    }
}
