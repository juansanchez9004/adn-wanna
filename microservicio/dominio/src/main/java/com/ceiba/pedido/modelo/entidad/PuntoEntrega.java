package com.ceiba.pedido.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public final class PuntoEntrega {

    private final String direccion;

    private final String municipio;

    private PuntoEntrega(String direccion, String municipio) {
        this.direccion = direccion;
        this.municipio = municipio;
    }

    public static PuntoEntrega crear(String direccion, String municipio) {
        ValidadorArgumento.validarObligatorio(direccion, "Direccion es requerida");
        ValidadorArgumento.validarObligatorio(municipio, "Municipio es requerido");
        return new PuntoEntrega(direccion, municipio);
    }

    public String entregaDomicilio() {
        return new StringBuilder(this.direccion)
                .append(" - ").append(this.municipio).toString();
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMunicipio() {
        return municipio;
    }
}
