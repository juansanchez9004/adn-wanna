package com.ceiba.pedido.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pedido.modelo.entidad.EstadoPedido;
import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;
import com.ceiba.pedido.modelo.entidad.PuntoEntrega;
import com.ceiba.producto.ProductoTestDataBuilder;
import com.ceiba.producto.entidad.TipoProducto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class PedidoTest {

    @Test
    void deberiaReconstruirElPedidoConEstadoCanceladoExitosamente() {

        Date fechaPedido = Date.from(LocalDate.of(2022, 5, 19)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoPerfume = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(1)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(650000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Mont Blanc Legend Eau de Parfum")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conId(10L)
                .conFecha(fechaPedido)
                .conCliente(cliente)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoPerfume)
                .conValorTotal(BigDecimal.valueOf(1820200))
                .conEstado(EstadoPedido.CANCELADO)
                .reconstruir();

        Assertions.assertEquals(10L, pedido.getId());
        Assertions.assertNotNull(pedido.getFecha());
        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(productoOrdenadoTipoPerfume, pedido.getProductosOrdenados().get(0));
        Assertions.assertEquals(1820200, pedido.getValorTotal().longValue());
        Assertions.assertTrue(pedido.esCancelado());
    }

    @Test
    void deberiaCrearElPedidoCorrectamenteConProductosTipoPerfumeSinDescuentoDiaMes() {

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

        var pedido = new PedidoTestDataBuilder()
                .conFecha(fechaPedido)
                .conCliente(cliente)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoPerfume)
                .crear();

        Assertions.assertNotNull(pedido.getFecha());
        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(productoOrdenadoTipoPerfume, pedido.getProductosOrdenados().get(0));
        Assertions.assertEquals(715000L, pedido.getValorTotal().longValue());
        Assertions.assertTrue(pedido.esPendiente());
    }

    @Test
    void deberiaCrearElPedidoCorrectamenteConProductosTipoPerfumeConDescuentoDiaMes() {

        Date fechaPedido = Date.from(LocalDate.of(2022, 5, 16)
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
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Acqua di Giò Profondo de Giorgio Armani")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conFecha(fechaPedido)
                .conCliente(cliente)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoPerfume)
                .crear();

        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertNotNull(pedido.getPuntoEntrega());
        Assertions.assertEquals(productoOrdenadoTipoPerfume, pedido.getProductosOrdenados().get(0));
        Assertions.assertEquals(682500L, pedido.getValorTotal().longValue());
        Assertions.assertTrue(pedido.esPendiente());
    }

    @Test
    void deberiaCrearElPedidoCorrectamenteConDiferentesProductosYDomicilioDel5Porciento() {

        Date fechaPedido = Date.from(LocalDate.of(2022, 5, 19)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoReloj = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(2)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(350000))
                        .conTipoProducto(TipoProducto.RELOJ).conNombre("Casio Retro Iconic Dorado")
                        .reconstruir())
                .build();

        ProductoOrdenado productoOrdenadoTipoPerfume = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(1)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(600000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Acqua di Giò Profondo de Giorgio Armani")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conFecha(fechaPedido)
                .conCliente(cliente)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoReloj)
                .conProducto(productoOrdenadoTipoPerfume)
                .crear();

        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(productoOrdenadoTipoReloj, pedido.getProductosOrdenados().get(0));
        Assertions.assertEquals(productoOrdenadoTipoPerfume, pedido.getProductosOrdenados().get(1));
        Assertions.assertEquals(1365000l, pedido.getValorTotal().longValue());
        Assertions.assertNotNull(pedido.getEstado());
        Assertions.assertTrue(pedido.esPendiente());
    }

    @Test
    void deberiaCrearElPedidoCorrectamenteConDiferentesProductosYDescuentoPorDiaMes() {

        Date fechaPedido = Date.from(LocalDate.of(2022, 5, 20)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoReloj = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(4)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(120000))
                        .conTipoProducto(TipoProducto.COSMETICO).conNombre("Kit Plantilla Maquillaje Cosmetico Delineador Cejas")
                        .reconstruir())
                .build();

        ProductoOrdenado productoOrdenadoTipoPerfume = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(1)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(600000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Acqua di Giò Profondo de Giorgio Armani")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conFecha(fechaPedido)
                .conCliente(cliente)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoReloj)
                .conProducto(productoOrdenadoTipoPerfume)
                .crear();

        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(productoOrdenadoTipoReloj, pedido.getProductosOrdenados().get(0));
        Assertions.assertEquals(productoOrdenadoTipoPerfume, pedido.getProductosOrdenados().get(1));
        Assertions.assertEquals(1058400L, pedido.getValorTotal().longValue());
        Assertions.assertTrue(pedido.esPendiente());
    }

    @Test
    void pedidoSinClienteDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder().conPedidoPorDefecto()
                        .conCliente(null)
                        .crear(), ExcepcionValorObligatorio.class,
                "El cliente es requerido");
    }

    @Test
    void pedidoSinPuntoEntregaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder().conPedidoPorDefecto()
                        .conPuntoEntrega(null)
                        .crear(), ExcepcionValorObligatorio.class,
                "El punto de entrega del pedido es requerido");
    }

    @Test
    void pedidoSinProductoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder().conPedidoPorDefecto()
                        .conProductos(Arrays.asList())
                        .crear(), ExcepcionValorObligatorio.class,
                "No se puede crear un pedido sin productos");
    }

    @Test
    void reconstruirSinIdDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder()
                        .conPedidoPorDefecto()
                        .conId(null)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "El id es requerido para ordenar");
    }

    @Test
    void reconstruirFacturaSinClienteDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder()
                        .conPedidoPorDefecto()
                        .conCliente(null)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "El cliente es requerido para ordenar");
    }

    @Test
    void reconstruirSinProductosDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder()
                        .conPedidoPorDefecto()
                        .conProductos(Arrays.asList())
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "No se puede crear un pedido sin productos");
    }

    @Test
    void reconstruirConValorTotalMenorACeroDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder()
                        .conPedidoPorDefecto()
                        .conValorTotal(BigDecimal.valueOf(-1))
                        .reconstruir(), ExcepcionValorInvalido.class,
                "El total a pagar no puede ser menor a cero");
    }

    @Test
    void pedidoDeberiaQuedarEnEstadoEntregado() {
        var pedido = new PedidoTestDataBuilder()
                .conPedidoPorDefecto()
                .conCliente(new ClienteTestDataBuilder()
                        .conClientePorDefecto()
                        .reconstruir())
                .crear();

        pedido.entregar();

        Assertions.assertTrue(pedido.esEntregado());
    }
}
