package com.ceiba.configuracion;

import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioEntregar;
import com.ceiba.pedido.servicio.ServicioOrdenar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioOrdenar servicioOrdenar(RepositorioPedido repositorioPedido) {
        return new ServicioOrdenar(repositorioPedido);
    }

    @Bean
    public ServicioEntregar servicioEntregar(RepositorioPedido repositorioPedido) {
        return new ServicioEntregar(repositorioPedido);
    }
}
