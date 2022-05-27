package com.ceiba.cliente;

import com.ceiba.cliente.modelo.dto.ClienteDTO;
import com.ceiba.producto.modelo.dto.ProductoDTO;

import java.math.BigDecimal;

public class ClienteDTOTestDataBuilder {

    private Long id;

    private String nombre;

    public ClienteDTOTestDataBuilder conClienteDTOPorDefecto(){
        this.id = 120l;
        this.nombre = "Cliente 120";
        return this;
    }

    public ClienteDTO reconstruir() {
        return new ClienteDTO(id, nombre);
    }

    public ClienteDTOTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ClienteDTOTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }
}
