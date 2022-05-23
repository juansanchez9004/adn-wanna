package com.ceiba.pedido.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pedido.modelo.dto.ResumenPedidoDTO;
import com.ceiba.pedido.puerto.dao.DaoPedido;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOPedidoMySQL implements DaoPedido {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoPedidoResumen mapeoPedidoResumen;

    @SqlStatement(namespace = "pedido", value = "obtenerentregados")
    private static String sqlObtenerEntregados;

    @SqlStatement(namespace = "pedido", value = "obtenerpendientes")
    private static String sqlObtenerPendientes;

    public DAOPedidoMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate,
                          MapeoPedidoResumen mapeoPedidoResumen) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoPedidoResumen = mapeoPedidoResumen;
    }

    @Override
    public List<ResumenPedidoDTO> obtenerResumenDePedidosEntregados() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerEntregados, mapeoPedidoResumen);
    }

    @Override
    public List<ResumenPedidoDTO> obtenerResumenDePedidosPendientes() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerPendientes, mapeoPedidoResumen);
    }
}
