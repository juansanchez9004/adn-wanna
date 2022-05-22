package com.ceiba.cliente.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public final class Cliente {

    private Long id;
    private final String nombre;

    private Cliente(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static Cliente reconstruir(Long id, String nombre) {
        ValidadorArgumento.validarObligatorio(nombre, "Nombre del cliente es requerido");
        ValidadorArgumento.validarObligatorio(id, "Id del cliente es requerido");
        return new Cliente(id, nombre);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
