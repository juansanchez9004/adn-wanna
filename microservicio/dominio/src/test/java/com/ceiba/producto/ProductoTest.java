package com.ceiba.producto;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.pedido.modelo.ProductoOrdenadoTestDataBuilder;
import com.ceiba.producto.entidad.TipoProducto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductoTest {

    @Test
    void deberiaReconstruirProductoExitosamente() {

        var producto = new ProductoTestDataBuilder()
                .conId(10l)
                .conNombre("Kit de limpieza facial")
                .conTipoProducto(TipoProducto.COSMETICO)
                .conValor(BigDecimal.valueOf(35500))
                .reconstruir();

        Assertions.assertEquals(10l, producto.getId());
        Assertions.assertEquals("Kit de limpieza facial", producto.getNombre());
        Assertions.assertEquals(TipoProducto.COSMETICO.name(), producto.getTipoProducto().name());
        Assertions.assertEquals(BigDecimal.valueOf(35500), producto.getValor());
    }

    @Test
    void reconstruirSinIdDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ProductoTestDataBuilder()
                        .conNombre("Kit de limpieza facial")
                        .conTipoProducto(TipoProducto.COSMETICO)
                        .conValor(BigDecimal.valueOf(35500))
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Id del producto es requerido");
    }

    @Test
    void reconstruirSinNombreDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ProductoTestDataBuilder()
                        .conId(25L)
                        .conTipoProducto(TipoProducto.COSMETICO)
                        .conValor(BigDecimal.valueOf(35500))
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Nombre del producto es requerido");
    }

    @Test
    void reconstruirSinTipoProductoDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ProductoTestDataBuilder()
                        .conId(25L)
                        .conNombre("Kit de limpieza")
                        .conValor(BigDecimal.valueOf(35500))
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Tipo Producto es requerido");
    }

    @Test
    void reconstruirSinValorDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ProductoTestDataBuilder()
                        .conId(25L)
                        .conNombre("Kit de limpieza")
                        .conTipoProducto(TipoProducto.COSMETICO)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "El valor del producto es requerido");
    }

    @Test
    void deberiaResponderEsProductoTipoRelojCorrectamente(){
        var producto = new ProductoTestDataBuilder()
                .conTipoProducto(TipoProducto.RELOJ)
                .conNombre("Cliente 1")
                .conId(1l)
                .conValor(BigDecimal.valueOf(65000))
                .reconstruir();

        Assertions.assertTrue(producto.esTipoReloj());
        Assertions.assertFalse(producto.esTipoCosmetico());
    }
}
