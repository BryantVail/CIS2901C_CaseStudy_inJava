-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 19, 2022 at 05:15 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `printshop_eatsleepprint`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `AddCustomer` (IN `emailAddress_param` VARCHAR(255), IN `firstName_param` VARCHAR(255), IN `lastName_param` VARCHAR(255))   BEGIN 

  Insert into Customers(
    EmailAddress, 
    FirstName, 
    LastName)
  Values(
    emailAddress_param, 
    firstName_param, 
    lastName_param );

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Customers_changeDeletedStatus` (IN `customerId` INT, IN `changeToDeleted` BOOLEAN)   Begin 
  Update customers 
    SET 
        Deleted = changeToDeleted, 
        LastUpdated = sysDate() 
    WHERE
        Id = customerId;
       
  (
    select * 
    from customers
    where Id = customerId);
    
End$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `customers_getByEmailAddress` (IN `emailAddress` VARCHAR(255))   BEGIN 
    select *
    from 
        customers 
    where customers.EmailAddress = emailAddress;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllCustomers` ()   BEGIN 
    SELECT *
    FROM Customers;

  END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCustomerById` (IN `id` INT)  DETERMINISTIC BEGIN
    SELECT * 
    FROM Customers 
    WHERE 
      Customers.Id = id;
  END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `customernotes`
--

CREATE TABLE `customernotes` (
  `Id` int(11) NOT NULL,
  `CustomerId` int(11) NOT NULL,
  `Note` varchar(1000) NOT NULL,
  `TimeStamp` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `Id` int(11) NOT NULL,
  `EmailAddress` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Deleted` bit(1) NOT NULL DEFAULT b'0',
  `CreatedDate` datetime NOT NULL DEFAULT sysdate(),
  `LastUpdated` datetime NOT NULL DEFAULT sysdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`Id`, `EmailAddress`, `FirstName`, `LastName`, `Deleted`, `CreatedDate`, `LastUpdated`) VALUES
(1020304, 'bryanttv@outlook.com', 'bryant', 'vail', b'0', '2022-09-21 21:19:07', '2022-10-18 23:05:22'),
(1020305, 'scFeliciano15@gmail.com', 'steph', 'vail', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020311, 'email@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020312, 'email_2022-09-20T20:37:06.240937100@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020313, 'email_2022-09-20T20:38:49.100530900@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020314, 'email_2022-09-20T20:39:13.574332600@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020315, 'email_2022-09-20T20:42:00.435754600@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020316, 'email_2022-09-20T21:01:10.895635@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020317, 'email_2022-09-20T21:02:07.853766300@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020318, 'email_2022-09-20T21:05:38.442995600@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020319, 'email_2022-09-20T21:06:16.613563500@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020320, 'email_2022-09-20T21:06:23.686407600@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020321, 'email_2022-09-20T21:12:23.565880300@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020322, 'email_2022-09-20T21:16:08.937368@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020323, 'email_2022-09-20T21:51:06.324727200@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020324, 'email_2022-09-20T21:53:00.151669300@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020325, 'email_2022-09-20T21:55:09.997228900@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020326, 'email_2022-09-20T22:23:48.145025@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020327, 'email_2022-09-20T22:31:40.091212700@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020328, 'email_2022-09-20T22:34:25.177314900@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020329, 'email_2022-09-20T22:37:38.320679600@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020330, 'email_2022-09-20T23:36:20.311971600@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020331, 'email_2022-09-20T23:37:57.828758@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020332, 'email_2022-09-20T23:56:53.537832400@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020333, 'email_2022-09-21T00:08:46.095068300@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020334, 'email_2022-09-21T00:09:00.118837@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020335, 'email_2022-09-21T15:10:28.569249600@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020336, 'bryantvail@gmail.com', 'bryant', 'test', b'0', '2022-09-21 21:19:07', '2022-10-18 23:05:16'),
(1020337, 'email_2022-09-21T18:45:44.738542500@gmail.com', 'firstName', 'lastName', b'0', '2022-09-21 21:19:07', '2022-09-21 21:19:52'),
(1020339, 'email_2022-09-22T00:11:06.447231300@gmail.com', 'firstName', 'lastName', b'0', '2022-09-22 00:11:06', '2022-09-22 00:11:06'),
(1020341, 'email_2022-10-04T19:13:39.770165600@gmail.com', 'firstName', 'lastName', b'0', '2022-10-04 19:13:39', '2022-10-04 19:13:39'),
(1020343, 'email_2022-10-04T19:23:12.523483700@gmail.com', 'firstName', 'lastName', b'0', '2022-10-04 19:23:12', '2022-10-04 19:23:12'),
(1020345, 'email_2022-10-04T20:12:53.357833500@gmail.com', 'firstName', 'lastName', b'0', '2022-10-04 20:12:53', '2022-10-04 20:12:53'),
(1020347, 'email_2022-10-04T21:16:38.237199800@gmail.com', 'firstName', 'lastName', b'0', '2022-10-04 21:16:38', '2022-10-04 21:16:38'),
(1020349, 'email_2022-10-04T21:19:15.224620300@gmail.com', 'firstName', 'lastName', b'0', '2022-10-04 21:19:15', '2022-10-04 21:19:15'),
(1020351, 'email_2022-10-04T21:20:58.984016600@gmail.com', 'firstName', 'lastName', b'0', '2022-10-04 21:20:59', '2022-10-04 21:20:59'),
(1020353, 'email_2022-10-06T21:07:59.773580600@gmail.com', 'firstName', 'lastName', b'0', '2022-10-06 21:07:59', '2022-10-06 21:07:59'),
(1020355, 'email_2022-10-06T21:54:47.437548@gmail.com', 'firstName', 'lastName', b'0', '2022-10-06 21:54:47', '2022-10-06 21:54:47'),
(1020357, 'email_2022-10-06T21:57:29.877698900@gmail.com', 'firstName', 'lastName', b'0', '2022-10-06 21:57:29', '2022-10-06 21:57:29'),
(1020359, 'email_2022-10-06T21:58:32.566465100@gmail.com', 'firstName', 'lastName', b'0', '2022-10-06 21:58:32', '2022-10-06 21:58:32'),
(1020361, 'email_2022-10-06T22:12:43.438890300@gmail.com', 'firstName', 'lastName', b'0', '2022-10-06 22:12:43', '2022-10-06 22:12:43'),
(1020363, 'email_2022-10-06T22:12:56.936837200@gmail.com', 'firstName', 'lastName', b'0', '2022-10-06 22:12:56', '2022-10-06 22:12:56'),
(1020365, 'email_2022-10-06T22:22:09.150370@gmail.com', 'firstName', 'lastName', b'0', '2022-10-06 22:22:09', '2022-10-06 22:22:09'),
(1020367, 'email_2022-10-06T22:39:55.064621800@gmail.com', 'firstName', 'lastName', b'0', '2022-10-06 22:39:55', '2022-10-06 22:39:55'),
(1020368, 'testEmail2022-10-12T22:51:48.592732800', 'testFirst', 'testLast', b'0', '2022-10-12 22:51:48', '2022-10-12 22:51:48'),
(1020370, 'email_2022-10-12T22:51:48.774296900@gmail.com', 'firstName', 'lastName', b'0', '2022-10-12 22:51:48', '2022-10-12 22:51:48'),
(1020371, 'testEmail2022-10-18T21:35:47.133931500', 'testFirst', 'testLast', b'0', '2022-10-18 21:35:47', '2022-10-18 21:35:47'),
(1020373, 'email_2022-10-18T21:35:47.286405@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 21:35:47', '2022-10-18 21:35:47'),
(1020374, 'testEmail2022-10-18T21:37:36.190933', 'testFirst', 'testLast', b'1', '2022-10-18 21:37:37', '2022-10-18 21:37:37'),
(1020375, 'testEmail2022-10-18T21:39:20.478644900', 'testFirst', 'testLast', b'1', '2022-10-18 21:39:21', '2022-10-18 21:39:21'),
(1020377, 'email_2022-10-18T21:46:33.003896800@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 21:46:33', '2022-10-18 21:46:33'),
(1020379, 'email_2022-10-18T22:06:41.883863600@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 22:06:41', '2022-10-18 22:06:41'),
(1020380, 'email_2022-10-18T22:08:16.733248800@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 22:08:17', '2022-10-18 22:08:17'),
(1020382, 'email_2022-10-18T22:08:26.109643400@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 22:08:26', '2022-10-18 22:08:26'),
(1020384, 'email_2022-10-18T22:08:48.484067100@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 22:08:48', '2022-10-18 22:08:48'),
(1020386, 'email_2022-10-18T22:27:24.879218500@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 22:27:24', '2022-10-18 22:27:24'),
(1020388, 'email_2022-10-18T22:30:48.641707500@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 22:30:48', '2022-10-18 22:30:48'),
(1020390, 'email_2022-10-18T22:36:23.145144900@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 22:36:23', '2022-10-18 22:36:23'),
(1020392, 'email_2022-10-18T22:37:21.105534300@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 22:37:21', '2022-10-18 22:37:21'),
(1020394, 'email_2022-10-18T22:37:34.432172100@gmail.com', 'firstName', 'lastName', b'0', '2022-10-18 22:37:34', '2022-10-18 22:37:34');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customernotes`
--
ALTER TABLE `customernotes`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `CustomerId` (`CustomerId`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `EmailAddress` (`EmailAddress`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customernotes`
--
ALTER TABLE `customernotes`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100000;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1020395;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customernotes`
--
ALTER TABLE `customernotes`
  ADD CONSTRAINT `customernotes_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
