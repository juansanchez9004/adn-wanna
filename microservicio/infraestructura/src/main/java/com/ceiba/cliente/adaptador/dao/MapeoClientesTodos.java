package com.ceiba.cliente.adaptador.dao;

import com.ceiba.cliente.modelo.dto.ClienteDTO;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoClientesTodos implements RowMapper<ClienteDTO>, MapperResult {

    @Override
    public ClienteDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var nombre = resultSet.getString("nombre");

        return new ClienteDTO(id, nombre);
    }
}
