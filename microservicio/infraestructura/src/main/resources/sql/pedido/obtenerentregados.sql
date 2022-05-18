select id, fecha, punto_entrega, valor_total, estado
from pedido
where estado = 'ENTREGADO'