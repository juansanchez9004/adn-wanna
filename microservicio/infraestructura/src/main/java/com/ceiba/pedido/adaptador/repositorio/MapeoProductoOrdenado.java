package com.ceiba.pedido.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;
import com.ceiba.producto.puerto.RepositorioProducto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoProductoOrdenado implements RowMapper<ProductoOrdenado>, MapperResult {

    private final RepositorioProducto repositorioProducto;

    public MapeoProductoOrdenado(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public ProductoOrdenado mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var idProducto = resultSet.getLong("id_producto");
        var cantidad = resultSet.getInt("cantidad");
        var valorTotal = resultSet.getBigDecimal("valor_total");

        return ProductoOrdenado.reconstruir(id, repositorioProducto.obtener(idProducto), cantidad, valorTotal);
    }
}
