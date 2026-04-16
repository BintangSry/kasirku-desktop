-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 16, 2026 at 04:47 PM
-- Server version: 8.0.30
-- PHP Version: 8.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kasir`
--

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id` int NOT NULL,
  `barcode` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `price` int NOT NULL,
  `stock` int NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `category` enum('makanan','minuman') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `barcode`, `name`, `price`, `stock`, `image`, `category`) VALUES
(11, 'MKN001', 'Croissant', 25000, 47, '/assets/Croissant.png', 'makanan'),
(12, 'MKN002', 'Sandwich', 35000, 50, '/assets/Sandwich.png', 'makanan'),
(13, 'MKN003', 'Toast', 25000, 34, '/assets/Toast.png', 'makanan'),
(14, 'MKN004', 'Muffin', 22000, 30, '/assets/Muffin.png', 'makanan'),
(15, 'MKN005', 'Brownie', 25000, 44, '/assets/Brownie.png', 'makanan'),
(16, 'MKN006', 'Cheesecake', 38000, 20, '/assets/Cheesecake.png', 'makanan'),
(17, 'MKN007', 'Pancake', 30000, 19, '/assets/Pancake.png', 'makanan'),
(18, 'MKN008', 'Waffle', 30000, 28, '/assets/Waffle.png', 'makanan'),
(19, 'MKN009', 'French Fries', 20000, 31, '/assets/French Fries.png', 'makanan'),
(20, 'MKN010', 'Pasta', 40000, 22, '/assets/Pasta.png', 'makanan'),
(21, 'MNM001', 'Espresso', 20000, 94, '/assets/Espresso.png', 'minuman'),
(22, 'MNM002', 'Americano', 25000, 88, '/assets/Americano.png', 'minuman'),
(23, 'MNM003', 'Cappuccino', 30000, 79, '/assets/Cappuccino.png', 'minuman'),
(24, 'MNM004', 'Latte', 30000, 60, '/assets/Latte.png', 'minuman'),
(25, 'MNM005', 'Mocha', 35000, 55, '/assets/Mocha.png', 'minuman'),
(26, 'MNM006', 'Macchiato', 38000, 69, '/assets/Macchiato.png', 'minuman'),
(27, 'MNM007', 'Cold Brew', 32000, 50, '/assets/Cold Brew.png', 'minuman'),
(28, 'MNM008', 'Iced Coffee', 25000, 32, '/assets/Iced Coffee.png', 'minuman'),
(29, 'MNM009', 'Tea', 20000, 120, '/assets/Tea.png', 'minuman'),
(30, 'MNM010', 'Hot Chocolate', 28000, 65, '/assets/Hot Chocolate.png', 'minuman'),
(31, 'MNM011', 'Coffee Milk', 20000, 10, '/assets/jawakutes.png', 'minuman');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int NOT NULL,
  `transaction_code` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `total` int DEFAULT NULL,
  `paid` int DEFAULT NULL,
  `change_amount` int DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `transaction_code`, `user_id`, `total`, `paid`, `change_amount`, `created_at`) VALUES
(1, 'TRX1775840703848', 1, 120000, 150000, 30000, '2026-04-11 00:05:03'),
(2, 'TRX1775987445180', 1, 179000, 200000, 21000, '2026-04-12 16:50:45'),
(3, 'TRX1776181485033', 1, 155000, 200000, 45000, '2026-04-14 22:44:45'),
(4, 'TRX1776215123552', 1, 45000, 50000, 5000, '2026-04-15 08:05:23');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_details`
--

CREATE TABLE `transaction_details` (
  `id` int NOT NULL,
  `transaction_id` int DEFAULT NULL,
  `menu_id` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `subtotal` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction_details`
--

INSERT INTO `transaction_details` (`id`, `transaction_id`, `menu_id`, `price`, `qty`, `subtotal`) VALUES
(1, 1, 28, 15000, 8, 120000),
(2, 2, 21, 5000, 5, 25000),
(3, 2, 26, 10000, 1, 10000),
(4, 2, 19, 14000, 1, 14000),
(5, 2, 15, 15000, 1, 15000),
(6, 2, 16, 17000, 5, 85000),
(7, 2, 17, 30000, 1, 30000),
(8, 3, 11, 25000, 2, 50000),
(9, 3, 22, 25000, 2, 50000),
(10, 3, 23, 30000, 1, 30000),
(11, 3, 13, 25000, 1, 25000),
(12, 4, 11, 25000, 1, 25000),
(13, 4, 21, 20000, 1, 20000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `role` enum('admin','kasir') COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'kasir', 'kasir', 'kasir'),
(3, 'bintang', 'bintang', 'kasir');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `barcode` (`barcode`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `transaction_details`
--
ALTER TABLE `transaction_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaction_id` (`transaction_id`),
  ADD KEY `menu_id` (`menu_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `transaction_details`
--
ALTER TABLE `transaction_details`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `transaction_details`
--
ALTER TABLE `transaction_details`
  ADD CONSTRAINT `transaction_details_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`),
  ADD CONSTRAINT `transaction_details_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
