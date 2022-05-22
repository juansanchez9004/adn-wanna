package com.ceiba.producto.consulta;

import com.ceiba.producto.modelo.dto.ProductoDTO;
import com.ceiba.producto.puerto.dao.DaoProducto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ManejadorConsultarProductosTodos {

    private final DaoProducto daoProducto;

    public ManejadorConsultarProductosTodos(DaoProducto daoProducto) {
        this.daoProducto = daoProducto;
    }

    public List<ProductoDTO> ejecutar() {
        return daoProducto.obtenerTodos();
    }
}
