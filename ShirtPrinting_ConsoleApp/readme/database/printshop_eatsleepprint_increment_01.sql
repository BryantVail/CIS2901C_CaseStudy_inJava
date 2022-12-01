-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 22, 2022 at 01:32 AM
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllCustomers` ()   BEGIN 
    SELECT *
    FROM Customers;

  END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCustomerById` (IN `id` INT)   BEGIN
    SELECT * 
    FROM Customers 
    WHERE 
      Id = id;
  END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCustomerBy_EmailAddress_FirstName_And_LastName` (IN `emailAddress_param` VARCHAR(255), IN `firstName_param` VARCHAR(255), IN `lastName_param` VARCHAR(255))   BEGIN 
    SELECT *
    From Customers customers 
    Where 
      EmailAddress = emailAddress_param 
      AND 
      FirstName = firstName_param 
      AND 
      LastName = lastName_param;

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
  `Deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`Id`, `EmailAddress`, `FirstName`, `LastName`, `Deleted`) VALUES
(1020304, 'bryanttv@outlook.com', 'bryant', 'vail', 0),
(1020305, 'scFeliciano15@gmail.com', 'steph', 'vail', 0),
(1020311, 'email@gmail.com', 'firstName', 'lastName', 0),
(1020312, 'email_2022-09-20T20:37:06.240937100@gmail.com', 'firstName', 'lastName', 0),
(1020313, 'email_2022-09-20T20:38:49.100530900@gmail.com', 'firstName', 'lastName', 0),
(1020314, 'email_2022-09-20T20:39:13.574332600@gmail.com', 'firstName', 'lastName', 0),
(1020315, 'email_2022-09-20T20:42:00.435754600@gmail.com', 'firstName', 'lastName', 0),
(1020316, 'email_2022-09-20T21:01:10.895635@gmail.com', 'firstName', 'lastName', 0),
(1020317, 'email_2022-09-20T21:02:07.853766300@gmail.com', 'firstName', 'lastName', 0),
(1020318, 'email_2022-09-20T21:05:38.442995600@gmail.com', 'firstName', 'lastName', 0),
(1020319, 'email_2022-09-20T21:06:16.613563500@gmail.com', 'firstName', 'lastName', 0),
(1020320, 'email_2022-09-20T21:06:23.686407600@gmail.com', 'firstName', 'lastName', 0),
(1020321, 'email_2022-09-20T21:12:23.565880300@gmail.com', 'firstName', 'lastName', 0),
(1020322, 'email_2022-09-20T21:16:08.937368@gmail.com', 'firstName', 'lastName', 0),
(1020323, 'email_2022-09-20T21:51:06.324727200@gmail.com', 'firstName', 'lastName', 0),
(1020324, 'email_2022-09-20T21:53:00.151669300@gmail.com', 'firstName', 'lastName', 0),
(1020325, 'email_2022-09-20T21:55:09.997228900@gmail.com', 'firstName', 'lastName', 0),
(1020326, 'email_2022-09-20T22:23:48.145025@gmail.com', 'firstName', 'lastName', 0),
(1020327, 'email_2022-09-20T22:31:40.091212700@gmail.com', 'firstName', 'lastName', 0),
(1020328, 'email_2022-09-20T22:34:25.177314900@gmail.com', 'firstName', 'lastName', 0),
(1020329, 'email_2022-09-20T22:37:38.320679600@gmail.com', 'firstName', 'lastName', 0),
(1020330, 'email_2022-09-20T23:36:20.311971600@gmail.com', 'firstName', 'lastName', 0),
(1020331, 'email_2022-09-20T23:37:57.828758@gmail.com', 'firstName', 'lastName', 0),
(1020332, 'email_2022-09-20T23:56:53.537832400@gmail.com', 'firstName', 'lastName', 0),
(1020333, 'email_2022-09-21T00:08:46.095068300@gmail.com', 'firstName', 'lastName', 0),
(1020334, 'email_2022-09-21T00:09:00.118837@gmail.com', 'firstName', 'lastName', 0),
(1020335, 'email_2022-09-21T15:10:28.569249600@gmail.com', 'firstName', 'lastName', 0),
(1020336, 'bryantvail@gmail.com', 'bryant', 'test', 0),
(1020337, 'email_2022-09-21T18:45:44.738542500@gmail.com', 'firstName', 'lastName', 0);

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
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1020338;

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
