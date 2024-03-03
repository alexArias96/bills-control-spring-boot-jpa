/* Populate tables */
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-28', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-28', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21', '');
INSERT INTO customers (name, surname, email, create_at, photo) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22', '');

/* Populate product table */
INSERT INTO products (name, price, create_at) VALUES('Panasonic Pantalla LCD', 500.50, NOW());
INSERT INTO products (name, price, create_at) VALUES('Tablets', 200.00, NOW());
INSERT INTO products (name, price, create_at) VALUES('Relojes', 100.00, NOW());
INSERT INTO products (name, price, create_at) VALUES('Televisores', 1500.00, NOW());
INSERT INTO products (name, price, create_at) VALUES('Consolas de sobremesa', 2000.00, NOW());
INSERT INTO products (name, price, create_at) VALUES('Ordenadores portátiles', 256.00, NOW());
INSERT INTO products (name, price, create_at) VALUES('Agitador de balanceo asimétrico 3D MTH-02, SBS', 325.00, NOW());
INSERT INTO products (name, price, create_at) VALUES('Aparato para test coagulación de leche ITCL-01', 660.00, NOW());

INSERT INTO bills (description, observation, customer_id ,create_at) VALUES('facturas equipos de oficina', null, 1, NOW());
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (1,1,1);
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (2,1,4);
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (1,1,5);
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (1,1,7);

INSERT INTO bills (description, observation, customer_id ,create_at) VALUES('factura Bicicleta', 'Alguna nota importante', 1, NOW());
INSERT INTO items_bills (amount, bill_id, product_id) VALUES (3,2,6);

INSERT INTO `users`(`username`, `password`, `enabled`) VALUES ('andrea','$2a$10$eDhcpxiIKaTQ0dcFGQ.Ad.dD7tG/GCp9lOSnCjBQIwMt6AcTyjOnG', 1);
INSERT INTO `users`(`username`, `password`, `enabled`) VALUES ('admin','$2a$10$MrwVbCfaVRsxuNxq6QuC9.CWDrZGPOL4jOqYrW7VaDvz61BCkZ/2O', 1);

INSERT INTO `authorities`(`user_id`, `authority`) VALUES (1, 'ROLE_USER');
INSERT INTO `authorities`(`user_id`, `authority`) VALUES (2, 'ROLE_ADMIN');
INSERT INTO `authorities`(`user_id`, `authority`) VALUES (2, 'ROLE_USER');