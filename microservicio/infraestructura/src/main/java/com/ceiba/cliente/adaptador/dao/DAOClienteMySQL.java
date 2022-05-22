package com.ceiba.cliente.adaptador.dao;

import com.ceiba.cliente.modelo.dto.ClienteDTO;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOClienteMySQL implements DaoCliente {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoClientesTodos mapeoClientesTodos;

    @SqlStatement(namespace = "cliente", value = "obtenertodos")
    private static String sqlObtenerTodos;

    public DAOClienteMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoClientesTodos mapeoClientesTodos) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoClientesTodos = mapeoClientesTodos;
    }

    @Override
    public List<ClienteDTO> obtenerTodos() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerTodos, mapeoClientesTodos);
    }
}
