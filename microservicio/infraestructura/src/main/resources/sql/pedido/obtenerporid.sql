select id, id_cliente, fecha, punto_entrega, valor_total, estado
from pedido
where id = :id