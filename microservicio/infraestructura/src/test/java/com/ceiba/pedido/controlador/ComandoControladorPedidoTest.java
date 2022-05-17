package com.ceiba.pedido.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorPedido.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorPedidoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioPedido repositorioPedido;

    @Test
    void crearPedidoExitoso() throws Exception {
        var comandoSolicitudOrdenarTestDataBuilder = new ComandoSolicitudOrdenarTestDataBuilder()
                            .crearPorDefecto()
                            .build();

        var resultado = mockMvc.perform(post("/pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoSolicitudOrdenarTestDataBuilder)))
                        .andExpect(status().is2xxSuccessful())
                        .andReturn();

        String jsonResult = resultado.getResponse().getContentAsString();
        var respuesta = objectMapper.readValue(jsonResult, RespuestaOrdenar.class);

        var pedidoGuardado = repositorioPedido.obtener(respuesta.getValor());

        Assertions.assertEquals("Carlos Manuel Lopez", pedidoGuardado.getCliente().getNombre());
        Assertions.assertEquals(2806500, pedidoGuardado.getValorTotal().longValue());
        Assertions.assertEquals(2, pedidoGuardado.getProductosOrdenados().size());
    }

    @Test
    void entregarPedidoExitoso() throws Exception {

        var comandoEntregarTestDataBuilder = new ComandoEntregarTestDataBuilder()
                .crearPorDefecto()
                .build();

        mockMvc.perform(post("/pedido/entregar/" + comandoEntregarTestDataBuilder.getIdPedido())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        var pedidoEntregado = repositorioPedido.obtener(comandoEntregarTestDataBuilder.getIdPedido());

        Assertions.assertTrue(pedidoEntregado.esEntregado());
    }
}
