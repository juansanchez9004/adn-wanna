package com.ceiba.pedido.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pedido.modelo.PedidoTestDataBuilder;
import com.ceiba.pedido.modelo.PuntoEntregaTestDataBuilder;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.modelo.entidad.PuntoEntrega;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

class ServicioEntregarTest {

    @Test
    void deberiaEntregarPedidoExitosamente() {

        Date fechaPedido = Date.from(LocalDate.of(2022, 5, 19)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        var pedido = new PedidoTestDataBuilder()
                .conPedidoPorDefecto()
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .reconstruir();

        var repositorioPedido = Mockito.mock(RepositorioPedido.class);
        Mockito.when(repositorioPedido.guardar(Mockito.any())).thenReturn(1l);

        var servicioEntregar = new ServicioEntregar(repositorioPedido);

        servicioEntregar.ejecutar(pedido);

        ArgumentCaptor<Pedido> captorPedido = ArgumentCaptor.forClass(Pedido.class);
        Mockito.verify(repositorioPedido, Mockito.times(1)).actualizarEstado(captorPedido.capture());
        Assertions.assertTrue(captorPedido.getValue().esEntregado());
    }

    @Test
    void entregaPedidoNullDeberiaLanzarError() {
        var RepositorioPedido = Mockito.mock(RepositorioPedido.class);
        var servicioEntregar = new ServicioEntregar(RepositorioPedido);

        BasePrueba.assertThrows(() -> servicioEntregar.
                ejecutar(null), ExcepcionValorObligatorio.class,
                "No existe un pedido para entregar");
    }
}
