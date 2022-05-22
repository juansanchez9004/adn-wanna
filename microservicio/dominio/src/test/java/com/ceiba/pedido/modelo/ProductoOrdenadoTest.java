package com.ceiba.pedido.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.producto.ProductoTestDataBuilder;
import com.ceiba.producto.modelo.entidad.TipoProducto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class ProductoOrdenadoTest {

    @Test
    void deberiaCrearExitoso() {
        var producto = new ProductoTestDataBuilder()
                            .conProductoPorDefecto()
                            .reconstruir();

        var productoOrdenar = new ProductoOrdenadoTestDataBuilder()
                .conProducto(producto)
                .conCantidad(2)
                .build();

        Assertions.assertEquals(producto, productoOrdenar.getProducto());
        Assertions.assertEquals(2, productoOrdenar.getCantidad());
        Assertions.assertEquals(BigDecimal.valueOf(1118000L), productoOrdenar.getValorTotal());
    }

    @Test
    void crearSinProductoDeberiaLanzarError() {
        BasePrueba.assertThrows(()->new ProductoOrdenadoTestDataBuilder()
                .conCantidad(5)
                .build(), ExcepcionValorObligatorio.class, "Producto es requerido");
    }

    @Test
    void crearSinCantidadDeberiaLanzarError() {
        var producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();

        BasePrueba.assertThrows(()->new ProductoOrdenadoTestDataBuilder()
                .conProducto(producto)
                .build(), ExcepcionValorObligatorio.class, "Cantidad es requerida");
    }

    @Test
    void deberiaReconstruirExitoso() {

        var producto = new ProductoTestDataBuilder()
                                                .conProductoPorDefecto()
                                                .reconstruir();

        var productoOrdenado = new ProductoOrdenadoTestDataBuilder()
                                        .conProducto(producto)
                                        .conId(100L)
                                        .conCantidad(2)
                                        .conValorTotal(BigDecimal.valueOf(258000))
                                        .reconstruir();

        Assertions.assertEquals(producto, productoOrdenado.getProducto());
        Assertions.assertEquals(2, productoOrdenado.getCantidad());
        Assertions.assertEquals(100l, productoOrdenado.getId());
        Assertions.assertEquals(BigDecimal.valueOf(258000), productoOrdenado.getValorTotal());
    }

    @Test
    void reconstruirSinProductoDeberiaLanzarError() {
        BasePrueba.assertThrows(()->new ProductoOrdenadoTestDataBuilder()
                .conCantidad(5)
                .reconstruir(), ExcepcionValorObligatorio.class, "Producto es requerido");
    }

    @Test
    void reconstruirSinCantidadDeberiaLanzarError() {
        var producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();

        BasePrueba.assertThrows(()->new ProductoOrdenadoTestDataBuilder()
                .conProducto(producto)
                .reconstruir(), ExcepcionValorObligatorio.class, "Cantidad es requerida");
    }

    @Test
    void reconstruirSinValorTotalDeberiaLanzarError() {
        var producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();

        BasePrueba.assertThrows(()->new ProductoOrdenadoTestDataBuilder()
                .conProducto(producto)
                .conCantidad(5)
                .reconstruir(), ExcepcionValorObligatorio.class, "Valor Total es requerida");
    }

    @Test
    void calcularValorTotalExitoso() {
        var producto = new ProductoTestDataBuilder().conProductoPorDefecto()
                .conValor(BigDecimal.valueOf(15500))
                .conTipoProducto(TipoProducto.COSMETICO)
                .reconstruir();

        var productoOrdenado = new ProductoOrdenadoTestDataBuilder()
                .conProducto(producto)
                .conCantidad(6)
                .build();

        var totalProductoOrdenado = productoOrdenado.calcularValorTotal(producto, 6);

        Assertions.assertEquals(BigDecimal.valueOf(93000).longValue(), totalProductoOrdenado.longValue());
    }
}
