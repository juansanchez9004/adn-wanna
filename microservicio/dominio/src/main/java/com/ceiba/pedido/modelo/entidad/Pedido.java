package com.ceiba.pedido.modelo.entidad;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.dominio.DescomponerFecha;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public final class Pedido {

    public static final BigDecimal PORCENTAJE_COSTO_DOMICILIO_COMUN = BigDecimal.valueOf(0.10);

    public static final BigDecimal PORCENTAJE_COSTO_DOMICILIO_TOPE_COMPRA = BigDecimal.valueOf(0.05);

    public static final BigDecimal VALOR_TOPE_COMPRA_PARA_DESCUENTO = BigDecimal.valueOf(1200000);

    protected static final List<Integer> DIAS_DEL_MES_PARA_DESCUENTO = Arrays.asList(10, 20);

    public static final BigDecimal PORCENTAJE_DESCUENTO_PARA_DIAS_DEL_MES = BigDecimal.valueOf(0.12);

    protected static final List<Integer> DIAS_DE_SEMANA_PARA_DESCUENTO_PERFUMES = Arrays.asList(1, 2); // 1 Lunes - 2 Martes

    public static final BigDecimal PORCENTAJE_DESCUENTO_PARA_PRODUCTOS_PERFUME = BigDecimal.valueOf(0.05);

    private Long id;

    private Cliente cliente;

    private LocalDate fecha;

    private PuntoEntrega puntoEntrega;

    private BigDecimal valorTotal;

    private BigDecimal valorSubTotal;

    private EstadoPedido estado;

    private List<ProductoOrdenado> productosOrdenados;

    private Pedido(LocalDate fecha, Cliente cliente, PuntoEntrega puntoEntrega, List<ProductoOrdenado> productosOrdenados) {
        this.cliente = cliente;
        this.puntoEntrega = puntoEntrega;
        this.productosOrdenados = new ArrayList<>(productosOrdenados);
        this.fecha = (fecha == null ? LocalDate.now() : fecha);
        this.valorSubTotal = calcularValorTotal(productosOrdenados);
        this.valorTotal = this.valorSubTotal;
        aplicarCostoDomicilio();
        aplicarDescuentoDiasPorMes();
        aplicarDescuentoDiasSemanaPerfume(productosOrdenados);
        this.estado = EstadoPedido.PENDIENTE;
    }

    private Pedido(Long id, LocalDate fecha, Cliente cliente, List<ProductoOrdenado> productosOrdenados, BigDecimal valorTotal, EstadoPedido estadoPedido) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.productosOrdenados = new ArrayList<>(productosOrdenados);
        this.valorTotal = valorTotal;
        this.estado = estadoPedido;
    }

    private void aplicarCostoDomicilio() {
        if(this.valorSubTotal.compareTo(VALOR_TOPE_COMPRA_PARA_DESCUENTO) > 0) {
            this.valorTotal = this.valorTotal.add(calcularCostoDomicilioPorTopeCompra());
        } else {
            this.valorTotal = this.valorTotal.add(calcularCostoDomicilioComun());
        }
    }

    private void aplicarDescuentoDiasPorMes() {
        this.valorTotal = this.valorTotal.subtract(calcularCostoDescuentoDiasPorMes());
    }

    private void aplicarDescuentoDiasSemanaPerfume(List<ProductoOrdenado> productosOrdenados) {
        this.valorTotal = this.valorTotal.subtract(calcularCostoDescuentoDiasSemanaPerfume(productosOrdenados));
    }

    public void entregar() {
        this.estado = EstadoPedido.ENTREGADO;
    }

    public BigDecimal calcularValorTotal(List<ProductoOrdenado> productosOrdenados) {
        return productosOrdenados.stream()
                .map(ProductoOrdenado::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularCostoDomicilioComun() {
        return this.valorSubTotal.multiply(PORCENTAJE_COSTO_DOMICILIO_COMUN);
    }

    public BigDecimal calcularCostoDomicilioPorTopeCompra() {
        return this.valorSubTotal.multiply(PORCENTAJE_COSTO_DOMICILIO_TOPE_COMPRA);
    }

    public BigDecimal calcularCostoDescuentoDiasPorMes() {
        if(esDiaPermitidoParaDescuentoPorMes(DescomponerFecha.porDia(this.fecha))) {
            return this.valorSubTotal.multiply(PORCENTAJE_DESCUENTO_PARA_DIAS_DEL_MES);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean esDiaPermitidoParaDescuentoPorMes(Integer dia) {
        return DIAS_DEL_MES_PARA_DESCUENTO.stream().anyMatch(diaDescuento -> diaDescuento.equals(dia));
    }

    public BigDecimal calcularCostoDescuentoDiasSemanaPerfume(List<ProductoOrdenado> productosOrdenados) {
        BigDecimal costoTotalProductoTipoPerfume = costoTotalProductosTipoPerfume(productosOrdenados);
        if(costoTotalProductoTipoPerfume.compareTo(BigDecimal.ZERO) > 0
                && esDiaSemanaPermitidoParaDescuentoPerfume(DescomponerFecha.porDiaDeSemana(this.fecha))) {
            return costoTotalProductoTipoPerfume.multiply(PORCENTAJE_DESCUENTO_PARA_PRODUCTOS_PERFUME);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal costoTotalProductosTipoPerfume(List<ProductoOrdenado> productosOrdenados) {
        return productosOrdenados.stream()
                .filter(productoOrdenado ->  productoOrdenado.getProducto().esTipoPermufe())
                .map(ProductoOrdenado::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean esDiaSemanaPermitidoParaDescuentoPerfume(Integer dia) {
        return DIAS_DE_SEMANA_PARA_DESCUENTO_PERFUMES.stream().anyMatch(diaSemanaDescuento -> diaSemanaDescuento.equals(dia));
    }

    public static Pedido crear(SolicitudOrdenar solicitudOrdenar) {
        ValidadorArgumento.validarObligatorio(solicitudOrdenar.getCliente(), "El cliente es requerido");
        ValidadorArgumento.validarObligatorio(solicitudOrdenar.getPuntoEntrega(), "El punto de entrega del pedido es requerido");
        ValidadorArgumento.validarNoVacio(solicitudOrdenar.getProductosOrdenados(), "No se puede crear un pedido sin productos");
        return new Pedido(solicitudOrdenar.getFecha(), solicitudOrdenar.getCliente(), solicitudOrdenar.getPuntoEntrega(), solicitudOrdenar.getProductosOrdenados());
    }

    public static Pedido reconstruir(Long id, LocalDate fecha, Cliente cliente, PuntoEntrega puntoEntrega, List<ProductoOrdenado> productosOrdenados, BigDecimal valorTotal, EstadoPedido estadoPedido) {
        ValidadorArgumento.validarObligatorio(id, "El id es requerido para ordenar");
        ValidadorArgumento.validarObligatorio(cliente, "El cliente es requerido para ordenar");
        ValidadorArgumento.validarObligatorio(puntoEntrega, "El punto de entrega es requerido para ordenar");
        ValidadorArgumento.validarNoVacio(productosOrdenados, "No se puede crear un pedido sin productos");
        if(valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExcepcionValorInvalido("El total a pagar no puede ser menor a cero");
        }
        return new Pedido(id, fecha, cliente, productosOrdenados, valorTotal, estadoPedido);
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public PuntoEntrega getPuntoEntrega() {
        return puntoEntrega;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public List<ProductoOrdenado> getProductosOrdenados() {
        return Collections.unmodifiableList(productosOrdenados);
    }

    public Boolean esPendiente() {
        return this.estado.equals(EstadoPedido.PENDIENTE);
    }

    public Boolean esEntregado() {
        return this.estado.equals(EstadoPedido.ENTREGADO);
    }

    public Boolean esCancelado() {
        return this.estado.equals(EstadoPedido.CANCELADO);
    }
}
