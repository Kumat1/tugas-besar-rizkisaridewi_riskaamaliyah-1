-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 11, 2015 at 12:36 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kereta_api`
--

-- --------------------------------------------------------

--
-- Table structure for table `harga`
--

CREATE TABLE IF NOT EXISTS `harga` (
  `kode_kota` varchar(5) NOT NULL,
  `asal` varchar(10) NOT NULL,
  `tujuan` varchar(30) NOT NULL,
  `bisnis` varchar(10) NOT NULL,
  `ekonomi` varchar(10) NOT NULL,
  PRIMARY KEY (`kode_kota`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `harga`
--

INSERT INTO `harga` (`kode_kota`, `asal`, `tujuan`, `bisnis`, `ekonomi`) VALUES
('kota1', 'Medan', 'Tanjung Balai', 'Rp. 75.000', 'Rp. 50.000'),
('kota2', 'Medan', 'Kisaran', 'Rp. 70.000', 'Rp. 45.000'),
('kota3', 'Medan', 'Sibolga', 'Rp. 60.000', 'Rp. 40.000'),
('kota4', 'Medan', 'Siantar', 'Rp. 65.000', 'Rp. 35.000'),
('kota5', 'Medan', 'Tebing Tinggi', 'Rp. 50.000', 'Rp. 30.000'),
('kota6', 'Medan', 'Perbaungan', 'Rp. 55.000', 'Rp. 25.000'),
('kota7', 'Medan', 'Batang Kuis', 'Rp. 40.000', 'Rp. 20.000');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `name` varchar(30) DEFAULT NULL,
  `pass` varchar(30) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`name`, `pass`, `level`) VALUES
('admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `retribusi`
--

CREATE TABLE IF NOT EXISTS `retribusi` (
  `biaya` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
