package com.ceiba.pedido.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;
import com.ceiba.pedido.puerto.repositorio.RepositorioProductoOrdenado;
import com.ceiba.producto.adaptador.repositorio.RepositorioProductoMySQL;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioProductoOrdenadoMySQL implements RepositorioProductoOrdenado {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoProductoOrdenado mapeoProductoOrdenado;

    @SqlStatement(namespace = "productoordenado", value = "crear")
    private static String sqlCrear;

    @SqlStatement(namespace = "productoordenado", value = "obtenerporpedido")
    private static String sqlObtenerPorPedido;

    public RepositorioProductoOrdenadoMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate,
                                            MapeoProductoOrdenado mapeoProductoOrdenado) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoProductoOrdenado = mapeoProductoOrdenado;
    }

    @Override
    public void guardarPorPedido(Pedido pedido, Long idPedido) {
        pedido.getProductosOrdenados().stream().forEach(productoOrdenado ->
                this.guardar(productoOrdenado, idPedido));
    }

    @Override
    public List<ProductoOrdenado> obtenerPorPedido(Long idPedido) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", idPedido);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPorPedido, parameterSource, mapeoProductoOrdenado);
    }

    private void guardar(ProductoOrdenado productoOrdenado, Long idPedido) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_pedido", idPedido);
        parameterSource.addValue("id_producto", productoOrdenado.getProducto().getId());
        parameterSource.addValue("valor_total", productoOrdenado.getValorTotal());
        parameterSource.addValue("cantidad", productoOrdenado.getCantidad());
        this.customNamedParameterJdbcTemplate.crear(parameterSource, sqlCrear);
    }
}
