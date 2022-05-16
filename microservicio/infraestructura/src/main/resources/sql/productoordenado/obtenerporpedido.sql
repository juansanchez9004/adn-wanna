select id, id_producto, valor_total, cantidad
from producto_ordenar
where id_pedido = :id_pedido