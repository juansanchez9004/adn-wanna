package com.ceiba.producto.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.producto.entidad.Producto;
import com.ceiba.producto.entidad.TipoProducto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoProducto implements RowMapper<Producto>, MapperResult {

    @Override
    public Producto mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        var id = resultSet.getLong("id");
        var nombre = resultSet.getString("nombre");
        var tipoProducto =  TipoProducto.valueOf(resultSet.getString("tipo_producto"));
        var valor = resultSet.getBigDecimal("valor");

        return Producto.reconstruir(id, nombre, tipoProducto, valor);
    }
}
