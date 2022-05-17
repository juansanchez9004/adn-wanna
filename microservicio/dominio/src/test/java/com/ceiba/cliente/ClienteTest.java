package com.ceiba.cliente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ClienteTest {

    @Test
    void deberiaReconstruirClienteExitosamente() {
        var cliente = new ClienteTestDataBuilder()
                .conId(10L)
                .conNombre("Jose Manuel Lopez")
                .reconstruir();

        Assertions.assertEquals(10l, cliente.getId());
        Assertions.assertEquals("Jose Manuel Lopez", cliente.getNombre());
    }

    @Test
    void reconstruirSinIdDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ClienteTestDataBuilder()
                                .conNombre("Jose Manuel Lopez")
                                .reconstruir(), ExcepcionValorObligatorio.class,
                "Id del cliente es requerido");
    }

    @Test
    void reconstruirSinNombreDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ClienteTestDataBuilder()
                        .conId(15L)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Nombre del cliente es requerido");
    }
}
