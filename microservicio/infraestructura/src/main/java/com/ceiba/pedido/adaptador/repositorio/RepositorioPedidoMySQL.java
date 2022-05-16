package com.ceiba.pedido.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioProductoOrdenado;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPedidoMySQL implements RepositorioPedido {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final RepositorioProductoOrdenado repositorioProductoOrdenado;

    private final MapeoPedido mapeoPedido;

    @SqlStatement(namespace = "pedido", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "pedido", value="obtenerporid")
    private static String sqlObtenerPorId;

    @SqlStatement(namespace = "pedido", value="actualizarestado.sql")
    private static String sqlActualizarEstado;

    public RepositorioPedidoMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate,
                                  RepositorioProductoOrdenado repositorioProductoOrdenado,
                                  MapeoPedido mapeoPedido) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.repositorioProductoOrdenado = repositorioProductoOrdenado;
        this.mapeoPedido = mapeoPedido;
    }

    @Override
    public Long guardar(Pedido pedido) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_cliente", pedido.getCliente().getId());
        parameterSource.addValue("fecha", pedido.getFecha());
        parameterSource.addValue("punto_entrega", pedido.getPuntoEntrega().entregaDomicilio());
        parameterSource.addValue("valor_total", pedido.getValorTotal());
        parameterSource.addValue("estado", pedido.getEstado());
        Long idPedidoGuardado = this.customNamedParameterJdbcTemplate.crear(parameterSource, sqlCrear);
        repositorioProductoOrdenado.guardarPorPedido(pedido, idPedidoGuardado);
        return idPedidoGuardado;
    }

    @Override
    public Pedido obtener(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlObtenerPorId, parameterSource, mapeoPedido));
    }

    @Override
    public void actualizarEstado(Pedido pedido) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", pedido.getId());
        parameterSource.addValue("estado", pedido.getEstado().toString());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .update(sqlActualizarEstado, parameterSource);
    }
}
