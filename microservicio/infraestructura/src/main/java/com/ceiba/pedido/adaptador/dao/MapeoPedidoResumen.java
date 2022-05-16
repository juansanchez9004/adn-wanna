package com.ceiba.pedido.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pedido.modelo.dto.ResumenPedidoDTO;
import com.ceiba.pedido.modelo.entidad.EstadoPedido;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoPedidoResumen implements RowMapper<ResumenPedidoDTO>, MapperResult {

    @Override
    public ResumenPedidoDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var valorTotal = resultSet.getBigDecimal("valor_total");
        var estado = EstadoPedido.valueOf(resultSet.getString("estado"));

        return new ResumenPedidoDTO(id, valorTotal, estado);
    }
}
