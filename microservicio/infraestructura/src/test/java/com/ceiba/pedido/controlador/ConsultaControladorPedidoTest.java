package com.ceiba.pedido.controlador;

import com.ceiba.ApplicationMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorPedido.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorPedidoTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    void consultarPedidosEntregados() throws Exception {

        mocMvc.perform(get("/pedido/entregados")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(3)))
                .andExpect(jsonPath("$[0].valorTotal", is(1071525.0)))
                .andExpect(jsonPath("$[0].fecha", is("2022-05-16")))
                .andExpect(jsonPath("$[0].sitioEntrega", is("Calle 90 c # 584 - Envigado")))
                .andExpect(jsonPath("$[0].estado", is("ENTREGADO")));
    }

    @Test
    void consultarPedidosPendientes() throws Exception {

        mocMvc.perform(get("/pedido/pendientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].valorTotal", is(1268960.0)))
                .andExpect(jsonPath("$[0].fecha", is("2022-05-16")))
                .andExpect(jsonPath("$[0].sitioEntrega", is("Calle 35 # 42 - 46 - Itagui")))
                .andExpect(jsonPath("$[0].estado", is("PENDIENTE")));
    }
}
