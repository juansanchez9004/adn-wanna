package com.ceiba.pedido.modelo.dto;

import com.ceiba.pedido.modelo.entidad.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ResumenPedidoDTO {
    private Long id;
    private BigDecimal valorTotal;
    private EstadoPedido estado;
}
