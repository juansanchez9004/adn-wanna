package com.ceiba.pedido.modelo;

import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.pedido.modelo.entidad.EstadoPedido;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.modelo.entidad.ProductoOrdenado;
import com.ceiba.pedido.modelo.entidad.PuntoEntrega;
import com.ceiba.pedido.servicio.SolicitudOrdenarTestDataBuilder;
import com.ceiba.producto.ProductoTestDataBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoTestDataBuilder {

    private Long id;
    private Cliente cliente;
    private LocalDate fecha;
    private PuntoEntrega puntoEntrega;
    private BigDecimal valorTotal;
    private BigDecimal valorSubTotal;
    private EstadoPedido estado;
    private List<ProductoOrdenado> productosOrdenados;

    public PedidoTestDataBuilder() {
        productosOrdenados = new ArrayList<>();
    }

    public PedidoTestDataBuilder conPedidoPorDefecto() {
        this.id = 2L;
        this.cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();
        this.conProducto(new ProductoOrdenadoTestDataBuilder()
                                    .conCantidad(3)
                                    .conProducto(producto)
                                    .conId(10L)
                                    .conValorTotal(BigDecimal.valueOf(855000))
                                    .reconstruir());
        this.fecha = LocalDate.now();
        this.puntoEntrega = new PuntoEntregaTestDataBuilder().conPuntoEntregaPorDefecto().reconstruir();
        this.valorTotal = BigDecimal.valueOf(1677000);
        this.estado = EstadoPedido.PENDIENTE;
        return this;
    }

    public PedidoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public PedidoTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoTestDataBuilder conFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public PedidoTestDataBuilder conPuntoEntrega(PuntoEntrega puntoEntrega) {
        this.puntoEntrega = puntoEntrega;
        return this;
    }

    public PedidoTestDataBuilder conValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public PedidoTestDataBuilder conValorSubTotal(BigDecimal valorSubTotal) {
        this.valorSubTotal = valorSubTotal;
        return this;
    }

    public PedidoTestDataBuilder conEstado(EstadoPedido estado) {
        this.estado = estado;
        return this;
    }

    public PedidoTestDataBuilder conProducto(ProductoOrdenado productoOrdenado) {
        this.productosOrdenados.add(productoOrdenado);
        return this;
    }

    public PedidoTestDataBuilder conProductos(List<ProductoOrdenado> productosOrdenados) {
        this.productosOrdenados = productosOrdenados;
        return this;
    }

    public Pedido crear() {
        return Pedido.crear(new SolicitudOrdenarTestDataBuilder()
                .conProductosOrdenados(productosOrdenados)
                .conCliente(cliente)
                .conFecha(fecha)
                .conPuntoEntrega(puntoEntrega)
                .build());
    }

    public Pedido reconstruir() {
        return Pedido.reconstruir(id, fecha, cliente, puntoEntrega, productosOrdenados, valorTotal, estado);
    }
}
