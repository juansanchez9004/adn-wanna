package com.ceiba.producto;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.producto.modelo.entidad.TipoProducto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductoDTOTest {

    @Test
    void deberiaReconstruirProductoDTOExitosamente() {

        var productoDTO = new ProductoDTOTestDataBuilder()
                .conId(10l)
                .conNombre("Kit de limpieza facial")
                .conTipoProducto("COSMETICO")
                .conValor(BigDecimal.valueOf(35500))
                .reconstruir();

        Assertions.assertEquals(10l, productoDTO.getId());
        Assertions.assertEquals("Kit de limpieza facial", productoDTO.getNombre());
        Assertions.assertEquals(TipoProducto.COSMETICO.name(), productoDTO.getTipoProducto());
        Assertions.assertEquals(BigDecimal.valueOf(35500), productoDTO.getValor());
    }
}
