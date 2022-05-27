package com.ceiba.pedido.modelo;

import com.ceiba.cliente.modelo.dto.ClienteDTO;
import com.ceiba.pedido.modelo.dto.ResumenPedidoDTO;
import com.ceiba.pedido.modelo.entidad.EstadoPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ResumenPedidoDTOTestDataBuilder {

    private Long id;

    private LocalDate fecha;

    private String sitioEntrega;

    private BigDecimal valorTotal;

    private EstadoPedido estado;

    public ResumenPedidoDTOTestDataBuilder conResumenPedidoDTOPorDefecto(){
        this.id = 120l;
        this.fecha = LocalDate.of(2022, 5, 26);
        this.sitioEntrega = "Calle 258 # 34 - 34 sur";
        this.valorTotal = BigDecimal.valueOf(152000);
        this.estado = EstadoPedido.PENDIENTE;
        return this;
    }

    public ResumenPedidoDTO reconstruir() {
        return new ResumenPedidoDTO(id, fecha, sitioEntrega, valorTotal, estado);
    }

    public ResumenPedidoDTOTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ResumenPedidoDTOTestDataBuilder conFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public ResumenPedidoDTOTestDataBuilder conSitioEntrega(String sitioEntrega) {
        this.sitioEntrega = sitioEntrega;
        return this;
    }

    public ResumenPedidoDTOTestDataBuilder conValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public ResumenPedidoDTOTestDataBuilder conEstado(EstadoPedido estado) {
        this.estado = estado;
        return this;
    }
}
