-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.5-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for carinsurance
DROP DATABASE IF EXISTS `carinsurance`;
CREATE DATABASE IF NOT EXISTS `carinsurance` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `carinsurance`;

-- Dumping structure for table carinsurance.addresses
DROP TABLE IF EXISTS `addresses`;
CREATE TABLE IF NOT EXISTS `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(70) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table carinsurance.addresses: ~1 rows (approximately)
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` (`id`, `address`) VALUES
	(1, 'st. Kurkovo');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;

-- Dumping structure for table carinsurance.authorities
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `username_authority` (`username`,`authority`),
  CONSTRAINT `authorities_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table carinsurance.authorities: ~0 rows (approximately)
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

-- Dumping structure for table carinsurance.car_details
DROP TABLE IF EXISTS `car_details`;
CREATE TABLE IF NOT EXISTS `car_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `cubic_capacity` int(11) NOT NULL,
  `registration_date` date DEFAULT NULL,
  `has_accidents` tinyint(1) NOT NULL,
  `driver_age` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table carinsurance.car_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `car_details` DISABLE KEYS */;
INSERT INTO `car_details` (`id`, `brand`, `model`, `cubic_capacity`, `registration_date`, `has_accidents`, `driver_age`, `is_active`) VALUES
	(1, 'Mercedes', 'S class', 1300, '2014-10-20', 1, 0, 1);
/*!40000 ALTER TABLE `car_details` ENABLE KEYS */;

-- Dumping structure for table carinsurance.multicriteria_range
DROP TABLE IF EXISTS `multicriteria_range`;
CREATE TABLE IF NOT EXISTS `multicriteria_range` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cc_min` int(11) NOT NULL,
  `cc_max` int(11) NOT NULL,
  `car_age_min` int(11) NOT NULL,
  `car_age_max` int(11) NOT NULL,
  `base_amount` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table carinsurance.multicriteria_range: ~4 rows (approximately)
/*!40000 ALTER TABLE `multicriteria_range` DISABLE KEYS */;
INSERT INTO `multicriteria_range` (`id`, `cc_min`, `cc_max`, `car_age_min`, `car_age_max`, `base_amount`) VALUES
	(1, 0, 1047, 0, 19, 403.25),
	(2, 0, 1047, 20, 100, 432.25),
	(3, 1048, 1309, 0, 19, 529.23),
	(4, 1048, 1309, 20, 100, 550.23);
/*!40000 ALTER TABLE `multicriteria_range` ENABLE KEYS */;

-- Dumping structure for table carinsurance.policy_details
DROP TABLE IF EXISTS `policy_details`;
CREATE TABLE IF NOT EXISTS `policy_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `picture` blob DEFAULT NULL,
  `phone_number` int(10) NOT NULL,
  `email` varchar(20) NOT NULL,
  `address_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `car_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `policy_details_user_details_id_fk` (`user_id`),
  KEY `policy_details_addresses_id_fk` (`address_id`),
  KEY `policy_details_car_details_id_fk` (`car_id`),
  CONSTRAINT `policy_details_addresses_id_fk` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `policy_details_car_details_id_fk` FOREIGN KEY (`car_id`) REFERENCES `car_details` (`id`),
  CONSTRAINT `policy_details_user_details_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table carinsurance.policy_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `policy_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `policy_details` ENABLE KEYS */;

-- Dumping structure for table carinsurance.policy_request
DROP TABLE IF EXISTS `policy_request`;
CREATE TABLE IF NOT EXISTS `policy_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `policy_id` int(11) NOT NULL,
  `user_details_id` int(11) NOT NULL,
  `is_approved` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `policy_request_policy_details_id_fk` (`policy_id`),
  KEY `policy_request_user_details_id_fk` (`user_details_id`),
  CONSTRAINT `policy_request_policy_details_id_fk` FOREIGN KEY (`policy_id`) REFERENCES `policy_details` (`id`),
  CONSTRAINT `policy_request_user_details_id_fk` FOREIGN KEY (`user_details_id`) REFERENCES `user_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table carinsurance.policy_request: ~0 rows (approximately)
/*!40000 ALTER TABLE `policy_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `policy_request` ENABLE KEYS */;

-- Dumping structure for table carinsurance.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `users_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table carinsurance.users: ~1 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
	('test@abv.bg', '{noop}what', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table carinsurance.user_details
DROP TABLE IF EXISTS `user_details`;
CREATE TABLE IF NOT EXISTS `user_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone_number` int(10) NOT NULL,
  `address_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_details_email_uindex` (`email`),
  KEY `user_details_addresses_id_fk` (`address_id`),
  CONSTRAINT `user_details_addresses_id_fk` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `user_details_users_username_fk` FOREIGN KEY (`email`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table carinsurance.user_details: ~1 rows (approximately)
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` (`id`, `email`, `first_name`, `last_name`, `phone_number`, `address_id`) VALUES
	(4, 'test@abv.bg', 'Test', 'Testov', 888888888, 1);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
