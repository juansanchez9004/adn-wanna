package com.ceiba.pedido.modelo.dto;

import com.ceiba.pedido.modelo.entidad.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
public class ResumenPedidoDTO {
    private Long id;
    private LocalDate fecha;
    private String sitioEntrega;
    private BigDecimal valorTotal;
    private EstadoPedido estado;
}
