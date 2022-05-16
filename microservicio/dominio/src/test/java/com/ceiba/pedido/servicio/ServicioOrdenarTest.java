package com.ceiba.pedido.servicio;

import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.pedido.modelo.ProductoOrdenadoTestDataBuilder;
import com.ceiba.pedido.modelo.PuntoEntregaTestDataBuilder;
import com.ceiba.pedido.modelo.SolicitudOrdenarTestDataBuilder;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;
import com.ceiba.pedido.modelo.entidad.PuntoEntrega;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.producto.ProductoTestDataBuilder;
import com.ceiba.producto.entidad.TipoProducto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ServicioOrdenarTest {

    @Test
    void deberiaGenerarPedidoYGuardarExitosamente(){

        Date fechaPedido = Date.from(LocalDate.of(2022, 5, 19)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoPerfume = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(1)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(650000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Mont Blanc Legend Eau de Parfum")
                        .reconstruir())
                .build();

        var solicitudOrdenar = new SolicitudOrdenarTestDataBuilder()
                .conProductoOrdenado(productoOrdenadoTipoPerfume)
                .conCliente(cliente)
                .conPuntoEntrega(puntoEntrega)
                .conFecha(fechaPedido)
                .build();

        var repositorioPedido = Mockito.mock(RepositorioPedido.class);
        Mockito.when(repositorioPedido.guardar(Mockito.any())).thenReturn(1l);

        var servicioOrdenar = new ServicioOrdenar(repositorioPedido);

        var idPedidoCreado = servicioOrdenar.ejecutar(solicitudOrdenar);

        ArgumentCaptor<Pedido> captorPedido = ArgumentCaptor.forClass(Pedido.class);
        Mockito.verify(repositorioPedido, Mockito.times(1)).guardar(captorPedido.capture());
        Assertions.assertEquals(cliente, captorPedido.getValue().getCliente());
        Assertions.assertEquals(productoOrdenadoTipoPerfume, captorPedido.getValue().getProductosOrdenados().get(0));
        Assertions.assertEquals(715000L, captorPedido.getValue().getValorTotal().longValue());
        Assertions.assertEquals(1l, idPedidoCreado);
    }
}
