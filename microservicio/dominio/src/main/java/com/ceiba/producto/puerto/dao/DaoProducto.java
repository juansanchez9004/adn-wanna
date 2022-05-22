package com.ceiba.producto.puerto.dao;

import com.ceiba.producto.modelo.dto.ProductoDTO;

import java.util.List;

public interface DaoProducto {

    List<ProductoDTO> obtenerTodos();
}
