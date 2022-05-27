package com.ceiba.pedido.modelo;

import com.ceiba.pedido.modelo.entidad.EstadoPedido;
import com.ceiba.producto.ProductoDTOTestDataBuilder;
import com.ceiba.producto.modelo.entidad.TipoProducto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class ResumenPedidoDTOTest {

    @Test
    void deberiaReconstruirResumenPedidoDTOExitosamente() {

        LocalDate fecha = LocalDate.of(2022, 05, 26);

        var resumenPedidoDTO = new ResumenPedidoDTOTestDataBuilder()
                .conId(10l)
                .conFecha(fecha)
                .conSitioEntrega("Cra 78 # 32 sur - Itagui")
                .conValorTotal(BigDecimal.valueOf(35500))
                .conEstado(EstadoPedido.ENTREGADO)
                .reconstruir();

        Assertions.assertEquals(10, resumenPedidoDTO.getId());
        Assertions.assertEquals(fecha, resumenPedidoDTO.getFecha());
        Assertions.assertEquals("Cra 78 # 32 sur - Itagui", resumenPedidoDTO.getSitioEntrega());
        Assertions.assertEquals(BigDecimal.valueOf(35500), resumenPedidoDTO.getValorTotal());
        Assertions.assertNotEquals(EstadoPedido.CANCELADO, resumenPedidoDTO.getEstado());
        Assertions.assertNotNull(resumenPedidoDTO.getEstado());
    }
}
