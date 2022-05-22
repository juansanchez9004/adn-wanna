package com.ceiba.cliente.consulta;

import com.ceiba.cliente.modelo.dto.ClienteDTO;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarClientesTodos {

    private final DaoCliente daoCliente;

    public ManejadorConsultarClientesTodos(DaoCliente daoCliente) {
        this.daoCliente = daoCliente;
    }

    public List<ClienteDTO> ejecutar() {
        return daoCliente.obtenerTodos();
    }
}
