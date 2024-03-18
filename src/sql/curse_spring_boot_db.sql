-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-03-2024 a las 22:46:39
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `curse_spring_boot_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities`
--

CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `authorities`
--

INSERT INTO `authorities` (`id`, `authority`, `user_id`) VALUES
(1, 'ROLE_USER', 1),
(2, 'ROLE_ADMIN', 2),
(3, 'ROLE_USER', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bills`
--

CREATE TABLE `bills` (
  `id` bigint(20) NOT NULL,
  `create_at` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `observation` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `bills`
--

INSERT INTO `bills` (`id`, `create_at`, `description`, `observation`, `customer_id`) VALUES
(1, '2024-03-13', 'facturas equipos de oficina', NULL, 1),
(2, '2024-03-13', 'factura Bicicleta', 'Alguna nota importante', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `create_at` date NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `customers`
--

INSERT INTO `customers` (`id`, `create_at`, `email`, `name`, `photo`, `surname`) VALUES
(1, '2017-08-28', 'profesor@bolsadeideas.com', 'Andres', '', 'Guzman'),
(2, '2017-08-28', 'john.doe@gmail.com', 'John', '', 'Doe'),
(3, '2017-08-03', 'linus.torvalds@gmail.com', 'Linus', '', 'Torvalds'),
(4, '2017-08-04', 'jane.doe@gmail.com', 'Jane', '', 'Doe'),
(5, '2017-08-05', 'rasmus.lerdorf@gmail.com', 'Rasmus', '', 'Lerdorf'),
(6, '2017-08-06', 'erich.gamma@gmail.com', 'Erich', '', 'Gamma'),
(7, '2017-08-07', 'richard.helm@gmail.com', 'Richard', '', 'Helm'),
(8, '2017-08-08', 'ralph.johnson@gmail.com', 'Ralph', '', 'Johnson'),
(9, '2017-08-09', 'john.vlissides@gmail.com', 'John', '', 'Vlissides'),
(10, '2017-08-10', 'james.gosling@gmail.com', 'James', '', 'Gosling'),
(11, '2017-08-11', 'bruce.lee@gmail.com', 'Bruce', '', 'Lee'),
(12, '2017-08-12', 'johnny.doe@gmail.com', 'Johnny', '', 'Doe'),
(13, '2017-08-13', 'john.roe@gmail.com', 'John', '', 'Roe'),
(14, '2017-08-14', 'jane.roe@gmail.com', 'Jane', '', 'Roe'),
(15, '2017-08-15', 'richard.doe@gmail.com', 'Richard', '', 'Doe'),
(16, '2017-08-16', 'janie.doe@gmail.com', 'Janie', '', 'Doe'),
(17, '2017-08-17', 'phillip.webb@gmail.com', 'Phillip', '', 'Webb'),
(18, '2017-08-18', 'stephane.nicoll@gmail.com', 'Stephane', '', 'Nicoll'),
(19, '2017-08-19', 'sam.brannen@gmail.com', 'Sam', '', 'Brannen'),
(20, '2017-08-20', 'juergen.Hoeller@gmail.com', 'Juergen', '', 'Hoeller'),
(21, '2017-08-21', 'janie.roe@gmail.com', 'Janie', '', 'Roe'),
(22, '2017-08-22', 'john.smith@gmail.com', 'John', '', 'Smith');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_bills`
--

CREATE TABLE `items_bills` (
  `id` bigint(20) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `items_bills`
--

INSERT INTO `items_bills` (`id`, `amount`, `product_id`, `bill_id`) VALUES
(1, 1, 1, 1),
(2, 2, 4, 1),
(3, 1, 5, 1),
(4, 1, 7, 1),
(5, 3, 6, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `product_id` bigint(20) NOT NULL,
  `create_at` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`product_id`, `create_at`, `name`, `price`) VALUES
(1, '2024-03-13', 'Panasonic Pantalla LCD', 500.5),
(2, '2024-03-13', 'Tablets', 200),
(3, '2024-03-13', 'Relojes', 100),
(4, '2024-03-13', 'Televisores', 1500),
(5, '2024-03-13', 'Consolas de sobremesa', 2000),
(6, '2024-03-13', 'Ordenadores portátiles', 256),
(7, '2024-03-13', 'Agitador de balanceo asimétrico 3D MTH-02, SBS', 325),
(8, '2024-03-13', 'Aparato para test coagulación de leche ITCL-01', 660);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `enabled`, `password`, `username`) VALUES
(1, b'1', '$2a$10$eDhcpxiIKaTQ0dcFGQ.Ad.dD7tG/GCp9lOSnCjBQIwMt6AcTyjOnG', 'andrea'),
(2, b'1', '$2a$10$MrwVbCfaVRsxuNxq6QuC9.CWDrZGPOL4jOqYrW7VaDvz61BCkZ/2O', 'admin');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKrimuuii4fm3j9qt8uupyiy8nd` (`user_id`,`authority`);

--
-- Indices de la tabla `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoy9sc2dmxj2qwjeiiilf3yuxp` (`customer_id`);

--
-- Indices de la tabla `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `items_bills`
--
ALTER TABLE `items_bills`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3ge771rt1vgcag2m94cabi4vi` (`product_id`),
  ADD KEY `FKq7fu105frju5fpilmkq92tj19` (`bill_id`);

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `authorities`
--
ALTER TABLE `authorities`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `bills`
--
ALTER TABLE `bills`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `items_bills`
--
ALTER TABLE `items_bills`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `products`
--
ALTER TABLE `products`
  MODIFY `product_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `FKk91upmbueyim93v469wj7b2qh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `FKoy9sc2dmxj2qwjeiiilf3yuxp` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Filtros para la tabla `items_bills`
--
ALTER TABLE `items_bills`
  ADD CONSTRAINT `FK3ge771rt1vgcag2m94cabi4vi` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  ADD CONSTRAINT `FKq7fu105frju5fpilmkq92tj19` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
