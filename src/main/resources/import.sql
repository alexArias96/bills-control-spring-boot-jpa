/* Populate tables */
INSERT INTO customers (id, name, surname, email, create_at, photo) VALUES(1, 'Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-28', '');
INSERT INTO customers (id, name, surname, email, create_at, photo) VALUES(2, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-28', '');

/* Populate product table */
INSERT INTO products (id,name, price, create_at) VALUES(1,'Panasonic Pantalla LCD', 500.50, NOW());
INSERT INTO products (id,name, price, create_at) VALUES(2,'Tablets', 200.00, NOW());
INSERT INTO products (id,name, price, create_at) VALUES(3,'Relojes', 100.00, NOW());
INSERT INTO products (id,name, price, create_at) VALUES(4,'Televisores', 1500.00, NOW());
INSERT INTO products (id,name, price, create_at) VALUES(5,'Consolas de sobremesa', 2000.00, NOW());
INSERT INTO products (id,name, price, create_at) VALUES(6,'Ordenadores portátiles', 256.00, NOW());
INSERT INTO products (id,name, price, create_at) VALUES(7,'Agitador de balanceo asimétrico 3D MTH-02, SBS', 325.00, NOW());
INSERT INTO products (id,name, price, create_at) VALUES(8,'Aparato para test coagulación de leche ITCL-01', 660.00, NOW());

INSERT INTO bills (description, observation, customer_id ,create_at) VALUES('facturas equipos de oficina', null, 1, NOW());
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (1,1,1);
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (2,1,4);
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (1,1,5);
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (1,1,7);

INSERT INTO bills (description, observation, customer_id ,create_at) VALUES('factura Bicicleta', 'Alguna nota importante', 1, NOW());
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (3,2,6);