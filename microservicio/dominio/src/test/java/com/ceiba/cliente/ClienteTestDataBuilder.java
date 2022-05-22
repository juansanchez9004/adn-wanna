package com.ceiba.cliente;

import com.ceiba.cliente.modelo.entidad.Cliente;

public class ClienteTestDataBuilder {

    private Long id;
    private String nombre;

    public ClienteTestDataBuilder conClientePorDefecto(){
        this.nombre = "Cliente 1";
        this.id = 1l;
        return this;
    }

    public Cliente reconstruir() {
        return Cliente.reconstruir(id, nombre);
    }

    public ClienteTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public ClienteTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }
}
