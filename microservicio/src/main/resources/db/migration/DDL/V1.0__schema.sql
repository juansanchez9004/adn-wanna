create table cliente (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 primary key (id)
);

create table producto (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 tipo_producto varchar(20) not null,
 valor DECIMAL(10,2) not null,
 primary key (id)
);

create table pedido (
 id int(11) not null auto_increment,
 id_cliente int(11) not null,
 fecha date not null,
 direccion_entrega varchar(20) not null,
 valor_total DECIMAL(10,2) not null,
 estado varchar(20) not null,
 primary key (id)
);

create table producto_ordenar (
 id int(11) not null auto_increment,
 id_pedido int(11) not null,
 id_producto int(11) not null,
 cantidad int(11) not null,
 primary key (id)
);

ALTER TABLE pedido
ADD CONSTRAINT cliente_fk
  FOREIGN KEY (id_cliente)
  REFERENCES cliente (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE producto_ordenar
ADD CONSTRAINT producto_fk
  FOREIGN KEY (id_producto)
  REFERENCES producto (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE producto_ordenar
ADD CONSTRAINT pedido_fk
  FOREIGN KEY (id_pedido)
  REFERENCES pedido (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;