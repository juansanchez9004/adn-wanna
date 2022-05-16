package com.ceiba.pedido.modelo;

import com.ceiba.pedido.modelo.entidad.PuntoEntrega;

public class PuntoEntregaTestDataBuilder {

    private String direccion;

    private String municipio;

    public PuntoEntregaTestDataBuilder conPuntoEntregaPorDefecto(){
        this.direccion = "Direccion 1";
        this.municipio = "Municipio 1";
        return this;
    }

    public PuntoEntrega reconstruir() {
        return PuntoEntrega.crear(direccion, municipio);
    }

    public PuntoEntregaTestDataBuilder conDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public PuntoEntregaTestDataBuilder conMunicipio(String municipio) {
        this.municipio = municipio;
        return this;
    }
}
