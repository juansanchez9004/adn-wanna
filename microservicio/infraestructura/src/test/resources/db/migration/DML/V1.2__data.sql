INSERT INTO pedido (id, id_cliente, fecha, punto_entrega, valor_total, estado) VALUES (1, 2, '2022-05-16', 'Calle 35 # 42 - 46 - Itagui', 1268960, 'PENDIENTE');
INSERT INTO pedido (id, id_cliente, fecha, punto_entrega, valor_total, estado) VALUES (2, 3, '2022-05-16', 'Diagonal 54 a # 14 - 5 - Medellin', 1839500, 'PENDIENTE');
INSERT INTO pedido (id, id_cliente, fecha, punto_entrega, valor_total, estado) VALUES (3, 1, '2022-05-16', 'Calle 90 c # 584 - Envigado', 1071525, 'ENTREGADO');

INSERT INTO producto_ordenar (id, id_pedido, id_producto, valor_total, cantidad) VALUES (1, 1, 2, 650000, 1);
INSERT INTO producto_ordenar (id, id_pedido, id_producto, valor_total, cantidad) VALUES (2, 1, 9, 286800, 3);
INSERT INTO producto_ordenar (id, id_pedido, id_producto, valor_total, cantidad) VALUES (3, 1, 5, 216800, 4);
INSERT INTO producto_ordenar (id, id_pedido, id_producto, valor_total, cantidad) VALUES (4, 2, 6, 780000, 1);
INSERT INTO producto_ordenar (id, id_pedido, id_producto, valor_total, cantidad) VALUES (5, 2, 7, 540000, 1);
INSERT INTO producto_ordenar (id, id_pedido, id_producto, valor_total, cantidad) VALUES (6, 2, 1, 480500, 1);
INSERT INTO producto_ordenar (id, id_pedido, id_producto, valor_total, cantidad) VALUES (7, 3, 7, 540000, 1);
INSERT INTO producto_ordenar (id, id_pedido, id_producto, valor_total, cantidad) VALUES (8, 3, 1, 480500, 1);
