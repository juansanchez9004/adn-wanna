package com.ceiba.producto.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.producto.modelo.dto.ProductoDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoProductosTodos implements RowMapper<ProductoDTO>, MapperResult {

    @Override
    public ProductoDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var nombre = resultSet.getString("nombre");
        var tipoProducto = resultSet.getString("tipo_producto");
        var valor = resultSet.getBigDecimal("valor");

        return new ProductoDTO(id, nombre, tipoProducto, valor);
    }
}
