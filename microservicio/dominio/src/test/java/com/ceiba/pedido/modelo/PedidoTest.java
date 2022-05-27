package com.ceiba.pedido.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pedido.modelo.entidad.EstadoPedido;
import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;
import com.ceiba.pedido.modelo.entidad.PuntoEntrega;
import com.ceiba.producto.ProductoTestDataBuilder;
import com.ceiba.producto.modelo.entidad.TipoProducto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

class PedidoTest {

    @Test
    void calcularValorTotalExitoso() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 19);

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoCosmetico = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(3)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(25800))
                        .conTipoProducto(TipoProducto.COSMETICO).conNombre("Kit de limpieza facial 100ml")
                        .reconstruir())
                .build();

        ProductoOrdenado productoOrdenadoTipoReloj = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(2)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(357000))
                        .conTipoProducto(TipoProducto.RELOJ).conNombre("Mont Blanc Legend Eau de Parfum")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(cliente)
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoCosmetico)
                .conProducto(productoOrdenadoTipoReloj)
                .crear();

        var valorTotalPedido = pedido.calcularValorTotal(pedido.getProductosOrdenados());

        Assertions.assertEquals(BigDecimal.valueOf(791400).longValue(), valorTotalPedido.longValue());
    }

    @Test
    void calcularCostoDomicilio10Porciento() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 19);

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoCosmetico = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(5)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(100000))
                        .conTipoProducto(TipoProducto.COSMETICO).conNombre("Kit de limpieza facial 100ml")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(cliente)
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoCosmetico)
                .crear();

        var costoDomicilioComun = pedido.calcularCostoDomicilioComun();

        Assertions.assertEquals(BigDecimal.valueOf(50000).longValue(), costoDomicilioComun.longValue());
    }

    @Test
    void calcularCostoDomicilio5Porciento() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 19);

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoCosmetico = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(3)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(500000))
                        .conTipoProducto(TipoProducto.COSMETICO).conNombre("Kit de limpieza facial 100ml")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(cliente)
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoCosmetico)
                .crear();

        var costoDomicilioEspecial = pedido.calcularCostoDomicilioPorTopeCompra();

        Assertions.assertEquals(BigDecimal.valueOf(75000).longValue(), costoDomicilioEspecial.longValue());
    }

    @Test
    void calcularCostoDescuentoDiasPorMes() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 20);

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoCosmetico = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(3)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(500000))
                        .conTipoProducto(TipoProducto.COSMETICO).conNombre("Kit de limpieza facial 100ml")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(cliente)
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoCosmetico)
                .crear();

        var costoDescuentoDeDiasPorMes = pedido.calcularCostoDescuentoDiasPorMes();

        Assertions.assertEquals(BigDecimal.valueOf(180000).longValue(), costoDescuentoDeDiasPorMes.longValue());
    }

    @Test
    void calcularCostoDescuentoDiasSemanaPerfume() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 16);

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoPerfumeAmber = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(2)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(515000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Amber Out Edition Golden")
                        .reconstruir())
                .build();

        ProductoOrdenado productoOrdenadoTipoCosmetico = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(3)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(500000))
                        .conTipoProducto(TipoProducto.COSMETICO).conNombre("Kit de limpieza facial 100ml")
                        .reconstruir())
                .build();

        ProductoOrdenado productoOrdenadoTipoPerfumeMontBlank = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(2)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(515000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Mont Blank Edition Fire")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(cliente)
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoPerfumeAmber)
                .conProducto(productoOrdenadoTipoCosmetico)
                .conProducto(productoOrdenadoTipoPerfumeMontBlank)
                .crear();

        var costoDescuentoDiasSemanaPerfume = pedido.calcularCostoDescuentoDiasSemanaPerfume(pedido.getProductosOrdenados());

        Assertions.assertEquals(BigDecimal.valueOf(103000).longValue(), costoDescuentoDiasSemanaPerfume.longValue());
    }

    @Test
    void calcularCostoTotalProductosTipoPerfume() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 18);

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 54 # 90 - 45")
                .conMunicipio("Itagui")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoPerfumeAmber = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(2)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(515000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Amber Out Edition Golden")
                        .reconstruir())
                .build();

        ProductoOrdenado productoOrdenadoTipoCosmetico = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(3)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(500000))
                        .conTipoProducto(TipoProducto.COSMETICO).conNombre("Kit de limpieza facial 100ml")
                        .reconstruir())
                .build();

        ProductoOrdenado productoOrdenadoTipoPerfumeMontBlank = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(3)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(418000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Mont Blank Edition Fire")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(cliente)
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoPerfumeAmber)
                .conProducto(productoOrdenadoTipoCosmetico)
                .conProducto(productoOrdenadoTipoPerfumeMontBlank)
                .crear();

        var costoTotalProductosTipoPerfume = pedido.costoTotalProductosTipoPerfume(pedido.getProductosOrdenados());

        Assertions.assertEquals(BigDecimal.valueOf(2284000).longValue(), costoTotalProductosTipoPerfume.longValue());
    }

    @Test
    void aplicaCostoDescuentoDiasSemanaPerfume() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 24);

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 88 # 90 - 41")
                .conMunicipio("Barbosa")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoPerfumeAmber = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(2)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(30000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Amber Out Edition Golden")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(cliente)
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoPerfumeAmber)
                .crear();

        var costoDescuentoDiasSemanaPerfume = pedido.calcularCostoDescuentoDiasSemanaPerfume(pedido.getProductosOrdenados());

        Assertions.assertEquals(3000L, costoDescuentoDiasSemanaPerfume.longValue());
    }

    @Test
    void noAplicaCostoDescuentoDiasSemanaPerfumePorNoSerPerfume() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 24);

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 88 # 90 - 41")
                .conMunicipio("Barbosa")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoPerfumeAmber = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(1)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(600000))
                        .conTipoProducto(TipoProducto.RELOJ).conNombre("FOSSIL BLUE")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(cliente)
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoPerfumeAmber)
                .crear();

        var costoDescuentoDiasSemanaPerfume = pedido.calcularCostoDescuentoDiasSemanaPerfume(pedido.getProductosOrdenados());

        Assertions.assertEquals(BigDecimal.ZERO, costoDescuentoDiasSemanaPerfume);
    }

    @Test
    void noAplicaCostoDescuentoDiasSemanaPerfumePorNoSerDiaPermitido() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 28);

        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();

        PuntoEntrega puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Calle 88 # 90 - 41")
                .conMunicipio("Barbosa")
                .reconstruir();

        ProductoOrdenado productoOrdenadoTipoPerfumeAmber = new ProductoOrdenadoTestDataBuilder()
                .conCantidad(1)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(600000))
                        .conTipoProducto(TipoProducto.PERFUME).conNombre("Amber Out Edition Golden")
                        .reconstruir())
                .build();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(cliente)
                .conFecha(fechaPedido)
                .conPuntoEntrega(puntoEntrega)
                .conProducto(productoOrdenadoTipoPerfumeAmber)
                .crear();

        var costoDescuentoDiasSemanaPerfume = pedido.calcularCostoDescuentoDiasSemanaPerfume(pedido.getProductosOrdenados());

        Assertions.assertEquals(BigDecimal.ZERO, costoDescuentoDiasSemanaPerfume);
    }

    @Test
    void deberiaReconstruirElPedidoConEstadoCanceladoExitosamente() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 19);

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

        LocalDate fechaPedido = LocalDate.of(2022, 5, 19);

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
        Assertions.assertFalse(pedido.esEntregado());
    }

    @Test
    void deberiaCrearElPedidoCorrectamenteConProductosTipoPerfumeConDescuentoDiaMes() {

        LocalDate fechaPedido = LocalDate.of(2022, 5, 16);

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

        LocalDate fechaPedido = LocalDate.of(2022, 5, 19);

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

        LocalDate fechaPedido = LocalDate.of(2022, 5, 20);

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
        Assertions.assertFalse(pedido.esCancelado());
    }

    @Test
    void deberiaCrearPedidoConValorDomicilioComunCuandoSubtotalEsIgualAlTopeCompraParaDescuento() {
        LocalDate fechaPedido = LocalDate.of(2022, 5, 26);

        var producto = new ProductoTestDataBuilder()
                .conProductoPorDefecto()
                .conValor(BigDecimal.valueOf(600000))
                .reconstruir();

        var productoOrdenado = new ProductoOrdenadoTestDataBuilder()
                .conProducto(producto)
                .conCantidad(2)
                .conValorTotal(BigDecimal.valueOf(1200000))
                .reconstruir();

        var pedido = new PedidoTestDataBuilder()
                .conCliente(new ClienteTestDataBuilder()
                        .conClientePorDefecto()
                        .reconstruir())
                .conPuntoEntrega(new PuntoEntregaTestDataBuilder()
                        .conPuntoEntregaPorDefecto()
                        .reconstruir())
                .conProducto(productoOrdenado)
                .crear();

        Assertions.assertEquals(BigDecimal.valueOf(1320000.0), pedido.getValorTotal());
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
    void reconstruirSinPuntoDeEntregaError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder()
                        .conPedidoPorDefecto()
                        .conPuntoEntrega(null)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "El punto de entrega es requerido para ordenar");
    }

    @Test
    void reconstruirConValorTotalNegativoError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder()
                        .conPedidoPorDefecto()
                        .conValorTotal(BigDecimal.valueOf(-100000))
                        .reconstruir(), ExcepcionValorInvalido.class,
                "El total a pagar no puede ser menor a cero");
    }

    @Test
    void reconstruirConValorTotalCeroError() {
        BasePrueba.assertThrows(() -> new PedidoTestDataBuilder()
                        .conPedidoPorDefecto()
                        .conValorTotal(BigDecimal.valueOf(0))
                        .reconstruir(), ExcepcionValorInvalido.class,
                "El total a pagar no puede ser menor a cero");
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

        Assertions.assertFalse(pedido.esPendiente());
        Assertions.assertFalse(pedido.esCancelado());
    }
}
