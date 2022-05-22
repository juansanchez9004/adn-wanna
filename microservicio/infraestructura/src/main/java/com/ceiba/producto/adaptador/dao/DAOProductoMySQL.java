package com.ceiba.producto.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.producto.modelo.dto.ProductoDTO;
import com.ceiba.producto.puerto.dao.DaoProducto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOProductoMySQL implements DaoProducto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoProductosTodos mapeoProductosTodos;

    @SqlStatement(namespace = "producto", value = "obtenertodos")
    private static String sqlObtenerTodos;

    public DAOProductoMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate,
                            MapeoProductosTodos mapeoProductosTodos) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoProductosTodos = mapeoProductosTodos;
    }

    @Override
    public List<ProductoDTO> obtenerTodos() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerTodos, mapeoProductosTodos);
    }
}
