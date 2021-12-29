
INSERT INTO public.region(nombre) VALUES ('Sudamérica');
INSERT INTO public.region(nombre) VALUES ('Centroamérica');
INSERT INTO public.region(nombre) VALUES ('Norteamérica');
INSERT INTO public.region(nombre) VALUES ('Europa');
INSERT INTO public.region(nombre) VALUES ('Asia');
INSERT INTO public.region(nombre) VALUES ('África');
INSERT INTO public.region(nombre) VALUES ('Oceanía');
INSERT INTO public.region(nombre) VALUES ('Antártida');

INSERT INTO public.cliente(region_id, apellido, fecha_creacion, email, nombre) VALUES(1, 'Yupanqui Lozano', '2020-12-18 13:09:10.749', 'asdfas@as.com', 'Juan Carlos');
INSERT INTO public.cliente(region_id, apellido, fecha_creacion, email, nombre) VALUES(2, 'apellido2', '2020-12-18 13:09:12.448', 'aasdfsdfas@as.com', 'nombre2');
INSERT INTO public.cliente(region_id, apellido, fecha_creacion, email, nombre) VALUES(3, 'apellido3', '2020-12-18 13:09:14.318', 'correo3@as.com', 'nombre3');
INSERT INTO public.cliente(region_id, apellido, fecha_creacion, email, nombre) VALUES(4, 'apellido4', '2020-12-18 13:09:14.318', 'correo4@as.com', 'nombre4');
INSERT INTO public.cliente(region_id, apellido, fecha_creacion, email, nombre) VALUES(5, 'apellido5', '2020-12-18 13:09:14.318', 'correo5@as.com', 'nombre5');
INSERT INTO public.cliente(region_id, apellido, fecha_creacion, email, nombre) VALUES(6, 'apellido6', '2020-12-18 13:09:14.318', 'correo6@as.com', 'nombre6');
INSERT INTO public.cliente(region_id, apellido, fecha_creacion, email, nombre) VALUES(7,'apellido7', '2020-12-18 13:09:14.318', 'correo7@as.com', 'nombre7');
INSERT INTO public.cliente(region_id, apellido, fecha_creacion, email, nombre) VALUES(7, 'apellido8', '2020-12-18 13:09:14.318', 'correo8@as.com', 'nombre8');

INSERT INTO public.usuario (username, password, enabled, nombre, apellido_paterno, email) VALUES('usuario', '$2a$10$n1.LeTy4dgNDwUa2sWfIruJLiVm.20gAZJvNEd2ta0AlMQ35PBQ/m', TRUE, 'Juan Carlos', 'Yupanqui', 'jcyupanqui@dominio.com');
INSERT INTO public.usuario (username, password, enabled, nombre, apellido_paterno, email) VALUES('admin', '$2a$10$n1.LeTy4dgNDwUa2sWfIruJLiVm.20gAZJvNEd2ta0AlMQ35PBQ/m', TRUE, 'Bruce', 'Lee', 'blee@dominio.com');

INSERT INTO public.rol (nombre) VALUES ('ROLE_USER');
INSERT INTO public.rol (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO public.rol_usuario (usuario_id, rol_id) VALUES (1,1);
INSERT INTO public.rol_usuario (usuario_id, rol_id) VALUES (2,1);
INSERT INTO public.rol_usuario (usuario_id, rol_id) VALUES (2,2);

/* Populate tabla productos */
INSERT INTO producto (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO factura (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

INSERT INTO item_factura (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO item_factura (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO item_factura (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO item_factura (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO factura (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO item_factura (cantidad, factura_id, producto_id) VALUES(3, 2, 6);