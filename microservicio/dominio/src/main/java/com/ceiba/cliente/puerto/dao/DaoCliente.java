package com.ceiba.cliente.puerto.dao;

import com.ceiba.cliente.modelo.dto.ClienteDTO;

import java.util.List;

public interface DaoCliente {

    List<ClienteDTO> obtenerTodos();
}
