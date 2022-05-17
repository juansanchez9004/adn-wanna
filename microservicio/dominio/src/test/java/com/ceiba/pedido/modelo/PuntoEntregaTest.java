package com.ceiba.pedido.modelo;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PuntoEntregaTest {

    @Test
    void deberiaReconstruirPuntoEntregaExitosamente() {

        var puntoEntrega = new PuntoEntregaTestDataBuilder()
                .conDireccion("Diagonal 38a # 32 - 37")
                .conMunicipio("Envigado")
                .reconstruir();

        Assertions.assertEquals("Diagonal 38a # 32 - 37", puntoEntrega.getDireccion());
        Assertions.assertEquals("Envigado", puntoEntrega.getMunicipio());
        Assertions.assertEquals("Diagonal 38a # 32 - 37 - Envigado", puntoEntrega.entregaDomicilio());
    }

    @Test
    void reconstruirSinDireccionDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PuntoEntregaTestDataBuilder()
                        .conMunicipio("Barbosa")
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Direccion es requerida");
    }

    @Test
    void reconstruirSinMunicipioDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new PuntoEntregaTestDataBuilder()
                        .conDireccion("Diagonal 38a # 32 - 37")
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Municipio es requerido");
    }
}
