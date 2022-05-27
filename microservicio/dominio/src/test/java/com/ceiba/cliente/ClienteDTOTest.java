package com.ceiba.cliente;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClienteDTOTest {

    @Test
    void deberiaReconstruirClienteDTOExitosamente() {
        var clienteDTO = new ClienteDTOTestDataBuilder()
                .conId(101L)
                .conNombre("Claribel Mejia")
                .reconstruir();

        Assertions.assertEquals(101l, clienteDTO.getId());
        Assertions.assertEquals("Claribel Mejia", clienteDTO.getNombre());
    }
}
