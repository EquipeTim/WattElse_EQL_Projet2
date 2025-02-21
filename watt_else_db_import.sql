-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           5.7.36 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour wattelse
CREATE DATABASE IF NOT EXISTS `wattelse` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `wattelse`;

-- Listage de la structure de table wattelse. bank_account
CREATE TABLE IF NOT EXISTS `bank_account` (
  `id_bank_account` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `iban` varchar(254) NOT NULL,
  `account_owner_name` varchar(254) DEFAULT NULL,
  `bic_swift` varchar(254) DEFAULT NULL,
  `account_registration_date` timestamp DEFAULT NULL,
  `account_close_date` timestamp DEFAULT NULL,
  PRIMARY KEY (`id_bank_account`),
  KEY `FK_avoir` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.bank_account : ~0 rows (environ)
INSERT INTO `bank_account` (`id_bank_account`, `id_user`, `iban`, `account_owner_name`, `bic_swift`, `account_registration_date`, `account_close_date`) VALUES
	(1, 1, '6548311564694', 'Admin', 'LT1312', '2025-02-17 11:52:20', NULL),
	(2, 6, '5234-4321-5689-1312', 'sarah', '5', '2025-02-20 13:20:28', NULL),
	(3, 5, '5555-4321-5689-1312', 'arvidas', '5', '2025-02-20 13:21:11', NULL),
	(4, 4, '4444-4321-5689-1312', 'cyrine', '5', '2025-02-20 13:21:38', NULL),
	(5, 3, '3333-4321-5689-1312', 'jules', '5', '2025-02-20 13:22:38', NULL),
	(6, 2, '2222-4321-5689-1312', 'jules', '5', '2025-02-20 13:23:11', NULL),
	(7, 2, '4444-4321-5689-1312', 'cyrine', '5', '2025-02-20 13:25:20', NULL);

-- Listage de la structure de table wattelse. brand_car
CREATE TABLE IF NOT EXISTS `brand_car` (
  `id_brand` int(11) NOT NULL AUTO_INCREMENT,
  `brand_label` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_brand`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.brand_car : ~0 rows (environ)


-- Listage de la structure de table wattelse. cancellation_type
CREATE TABLE IF NOT EXISTS `reservation_cancellation_type` (
  `id_cancellation_type` int(11) NOT NULL AUTO_INCREMENT,
  `cancellation_label` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_cancellation_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.cancellation_type : ~0 rows (environ)

-- Listage de la structure de table wattelse. car
CREATE TABLE IF NOT EXISTS `car` (
  `id_car` int(11) NOT NULL AUTO_INCREMENT,
  `id_model_car` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_car_withdrawal` int(11) NULL,
  `license_plate_number` varchar(254) DEFAULT NULL,
  `registration_date_car` timestamp DEFAULT NULL,
  `remove_date_car` timestamp DEFAULT NULL,
  `max_electric_power` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_car`),
  KEY `FK_conduire` (`id_user`),
  KEY `FK_definir` (`id_model_car`),
  KEY `FK_withdraw` (`id_car_withdrawal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.car : ~0 rows (environ)

-- Listage de la structure de table wattelse. car_withdrawal_reason
CREATE TABLE IF NOT EXISTS `car_withdrawal_reason` (
  `id_car_withdrawal` int(11) NOT NULL AUTO_INCREMENT,
  `car_withdrawal_label` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_car_withdrawal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.car_withdrawal_reason : ~0 rows (environ)

-- Listage de la structure de table wattelse. charging_station
CREATE TABLE IF NOT EXISTS `charging_station` (
  `id_charging_station` int(11) NOT NULL AUTO_INCREMENT,
  `id_station_closing_type` int(11) DEFAULT NULL,
  `id_plug_type` int(11) NOT NULL,
  `id_city` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `id_owner_bank_account` int(11) DEFAULT NULL,
  `id_owner_credit_card` int(11) DEFAULT NULL,
  `power_charging_station` int(11) DEFAULT NULL,
  `registration_station_date` timestamp DEFAULT NULL,
  `closing_station_date` timestamp DEFAULT NULL,
  `address_charging_station` varchar(255) DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `emergency_phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_charging_station`),
  KEY `FK_disposer` (`id_plug_type`),
  KEY `FK_expliquer` (`id_station_closing_type`),
  KEY `FK_installer` (`id_user`),
  KEY `FK_localiser` (`id_city`),
  KEY `FK_prelever_account` (`id_owner_bank_account`),
  KEY `FK_prelever_card` (`id_owner_credit_card`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.charging_station : ~0 rows (environ)
INSERT INTO `charging_station` (`id_charging_station`, `id_station_closing_type`, `id_plug_type`, `id_city`, `id_user`,
 `id_owner_bank_account`, `id_owner_credit_card`, `power_charging_station`, `registration_station_date`, `closing_station_date`,
  `address_charging_station`, `longitude`, `latitude`, `emergency_phone`) VALUES
	    (1, NULL, 1, 1, 1, 1, NULL, NULL, '2025-02-10 23:00:00', NULL, '32 Rue Barbès', 2.32961, 48.8171, NULL),
    	(2, NULL, 3, 1, 1, 1, NULL, NULL, '2025-02-10 23:00:00', NULL, '6 Av. du Président Salvador', 2.32741, 48.8108, NULL),
    	(3, NULL, 5, 1, 1, 1, NULL, NULL, '2025-02-10 23:00:00', NULL, '79 Av. du Général Leclerc', 2.31631, 48.7811, NULL),
    	(4, NULL, 1, 5, 2, 6, NULL, 4, '2024-02-14 22:00:00', NULL, '22-12 Rue Pierre de Ronsard', 47.587, 1.31853, '0687952568'),
    	(5, NULL, 2, 4, 3, NULL, 1, 5, '2024-03-14 22:00:00', NULL, '12 Rue dArcole', 43.2897, 5.37675, '0687952568'),
    	(6, NULL, 3, 3, 1, 1, NULL, 6, '2024-03-17 22:00:00', NULL, '78 Rue Saint-Jérôme', 45.7475, 4.8402, '0687958868'),
    	(7, NULL, 4, 2, 2, 7, NULL, 7, '2024-04-17 20:00:00', NULL, '43 Rue Parmentier', 47.5774, 1.33592, '0787958868'),
    	(8, NULL, 5, 1, 3, NULL, 3, 8, '2025-01-17 22:00:00', NULL, '14 rue Cujas', 48.8479, 2.34275, '0787958868'),
    	(9, NULL, 5, 1, 6, NULL, 6, 8, '2025-01-17 22:00:00', NULL, '14 rue Cujas', 48.8479, 2.34275, '0787958868'),
    	(10, NULL, 5, 1, 5, NULL, 5, 8, '2025-01-17 22:00:00', NULL, '14 rue Cujas', 48.8479, 2.34275, '0787958868');
-- Listage de la structure de table wattelse. city
CREATE TABLE IF NOT EXISTS `city` (
  `id_city` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(254) DEFAULT NULL,
  `postal_code` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_city`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.city : ~0 rows (environ)
INSERT INTO `city` (`id_city`, `city`, `postal_code`) VALUES
	(1, 'Paris', '75001'),
	(2, 'Blois', '45000'),
	(3, 'Lyon', '69000'),
	(4, 'Marseille', '13001'),
	(5, 'Cannes', '06400'),
	(6, 'Blois', '45000'),
	(7, 'Cannes', '06400'),
	(8, 'Marseille', '13001'),
	(9, 'Nice', '55000'),
	(10, 'Bordeaux', '80000');



-- Listage de la structure de table wattelse. closing_account_user_type
CREATE TABLE IF NOT EXISTS `closing_account_user_type` (
  `id_label_closing_account_user` int(11) NOT NULL AUTO_INCREMENT,
  `label_closing_account_user` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_label_closing_account_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.closing_account_user_type : ~0 rows (environ)

-- Listage de la structure de table wattelse. credit_card
CREATE TABLE IF NOT EXISTS `credit_card` (
  `id_credit_card` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `number_card` varchar(20) NOT NULL,
  `cardholder_name` varchar(254) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `cvv_number` int(11) DEFAULT NULL,
  `registration_date_carte` timestamp DEFAULT NULL,
  `withdrawal_date_card` timestamp DEFAULT NULL,
  PRIMARY KEY (`id_credit_card`),
  KEY `FK_posseder` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.credit_card : ~0 rows (environ)
INSERT INTO `credit_card` (`id_credit_card`, `id_user`, `number_card`, `cardholder_name`, `expiration_date`, `cvv_number`, `registration_date_carte`, `withdrawal_date_card`) VALUES
	(1, 3, '8924-4321-5689-1312', 'sara', '2025-10-12', 4588, '2025-02-20 13:14:13', NULL),
	(2, 1, '1111-4321-5689-1312', 'adm', '2025-10-12', 1111, '2025-02-20 13:15:36', NULL),
	(3, 3, '2222-4321-5689-1312', 'adm', '2025-10-12', 2222, '2025-02-20 13:16:42', NULL),
	(4, 4, '4444-4321-5689-1312', 'adm', '2025-10-12', 4444, '2025-02-20 13:17:22', NULL),
	(5, 5, '5555-4321-5689-1312', 'adm', '2025-10-12', 5555, '2025-02-20 13:18:57', NULL),
	(6, 6, '6655-4321-5689-1312', 'adm', '2025-10-12', 6666, '2025-02-20 13:19:28', NULL);

-- Listage de la structure de table wattelse. day
CREATE TABLE IF NOT EXISTS `day` (
  `id_day` int(11) NOT NULL AUTO_INCREMENT,
  `day` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_day`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.day : ~0 rows (environ)

-- Listage de la structure de table wattelse. evaluation
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id_evaluation` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_transaction` int(11) DEFAULT NULL,
  `id_type_evaluation` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_evaluation`),
  KEY `FK_choisir` (`id_type_evaluation`),
  KEY `FK_evaluer` (`id_user`),
  KEY `FK_ratingr` (`id_transaction`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.evaluation : ~0 rows (environ)

-- Listage de la structure de table wattelse. evaluation_type
CREATE TABLE IF NOT EXISTS `evaluation_type` (
  `id_type_evaluation` int(11) NOT NULL AUTO_INCREMENT,
  `evaluation_label` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_type_evaluation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.evaluation_type : ~0 rows (environ)

-- Listage de la structure de table wattelse. model_car
CREATE TABLE IF NOT EXISTS `model_car` (
  `id_model_car` int(11) NOT NULL AUTO_INCREMENT,
  `id_plug_type` int(11) NOT NULL,
  `id_brand` int(11) NOT NULL,
  `car_model_label` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_model_car`),
  KEY `FK_equiper` (`id_plug_type`),
  KEY `FK_fabriquer` (`id_brand`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.model_car : ~0 rows (environ)

-- Listage de la structure de table wattelse. opening_hour
CREATE TABLE IF NOT EXISTS `opening_hour` (
  `id_opening_hour` int(11) NOT NULL AUTO_INCREMENT,
  `id_charging_station` int(11) NOT NULL,
  `id_day` int(11) NOT NULL,
  `start_hour` time DEFAULT NULL,
  `end_hour` time DEFAULT NULL,
  `start_validity_date_opening_hour` date DEFAULT NULL,
  `end_validity_date_opening_hour` date DEFAULT NULL,
  PRIMARY KEY (`id_opening_hour`),
  KEY `FK_assigner` (`id_day`),
  KEY `FK_ouvrir` (`id_charging_station`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.opening_hour : ~0 rows (environ)
INSERT INTO `opening_hour` (`id_opening_hour`, `id_charging_station`, `id_day`, `start_hour`, `end_hour`, `start_validity_date_opening_hour`, `end_validity_date_opening_hour`) VALUES
	(1, 1, 2, '08:00:00', '17:00:00', '2025-02-01', '2026-02-18'),
	(2, 1, 5, '10:00:00', '15:15:15', '2025-02-01', NULL),
	(3, 1, 1, '08:10:55', '12:01:10', '2025-02-01', NULL),
	(4, 1, 1, '14:00:05', '18:00:00', '2025-02-01', NULL),
	(5, 1, 3, '08:00:00', '18:00:00', '2025-02-01', NULL),
	(6, 1, 6, '12:00:00', '15:00:00', '2025-02-01', NULL),
	(7, 1, 7, '06:00:00', '23:59:00', '2025-02-01', '2025-02-17'),
	(8, 1, 4, '15:00:00', '20:00:00', '2025-02-01', NULL),
	(9, 2, 2, '15:00:00', '20:00:00', '2025-02-01', '2025-05-20'),
	(10, 2, 1, '10:08:00', '18:42:50', '2025-02-01', NULL),
	(11, 2, 3, '00:00:00', '23:59:00', '2025-02-01', '2025-02-19'),
	(12, 2, 6, '09:15:25', '17:35:00', '2025-02-01', NULL),
	(13, 2, 7, '10:50:25', '21:21:21', '2025-03-21', '2025-06-20'),
	(14, 2, 4, '09:30:00', '11:59:00', '2025-02-01', NULL),
	(15, 3, 5, '10:30:00', '19:45:39', '2025-02-18', '2025-02-20'),
	(16, 10, 3, '08:00:00', '17:00:00', '2025-02-02', NULL),
	(17, 9, 4, '09:00:00', '18:00:00', '2025-02-03', '2025-03-20'),
	(18, 8, 5, '10:00:00', '19:00:00', '2025-02-04', '2025-02-22'),
	(19, 7, 6, '11:00:00', '20:00:00', '2025-02-05', NULL),
	(20, 6, 1, '12:00:00', '21:00:00', '2025-02-06', '2025-02-10'),
	(21, 10, 4, '08:00:00', '17:00:00', '2025-02-02', '2024-02-20'),
	(22, 9, 5, '09:00:00', '18:00:00', '2025-02-03', NULL),
	(23, 8, 6, '10:00:00', '19:00:00', '2025-02-04', '2026-02-21'),
	(24, 7, 7, '11:00:00', '20:00:00', '2025-02-05', '2025-05-20'),
	(25, 6, 2, '12:00:00', '21:00:00', '2025-02-06', '2027-02-20'),
	(26, 10, 5, '08:00:00', '17:00:00', '2025-02-02', NULL),
	(27, 9, 6, '09:00:00', '18:00:00', '2025-02-03', '2024-09-20'),
	(28, 8, 7, '10:00:00', '19:00:00', '2025-02-04', '2024-05-20'),
	(29, 7, 1, '11:00:00', '20:00:00', '2025-02-05', '2025-09-20'),
	(30, 6, 3, '12:00:00', '21:00:00', '2025-02-06', '2025-02-05');

-- Listage de la structure de table wattelse. payment
CREATE TABLE IF NOT EXISTS `payment` (
  `id_payment` int(11) NOT NULL AUTO_INCREMENT,
  `id_bank_account` int(11) DEFAULT NULL,
  `id_credit_card` int(11) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `payment_amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_payment`),
  KEY `FK_crediter` (`id_bank_account`),
  KEY `FK_verser` (`id_credit_card`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.payment : ~0 rows (environ)

-- Listage de la structure de table wattelse. payment_refuse_type
CREATE TABLE IF NOT EXISTS `payment_refuse_type` (
  `id_payment_refuse_type` int(11) NOT NULL AUTO_INCREMENT,
  `refuse_payment_label` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_payment_refuse_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.payment_refuse_type : ~0 rows (environ)

-- Listage de la structure de table wattelse. plug_type
CREATE TABLE IF NOT EXISTS `plug_type` (
  `id_plug_type` int(11) NOT NULL AUTO_INCREMENT,
  `plug_type` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_plug_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.plug_type : ~0 rows (environ)

-- Listage de la structure de table wattelse. pricing
CREATE TABLE IF NOT EXISTS `pricing` (
  `id_pricing` int(11) NOT NULL AUTO_INCREMENT,
  `id_type_pricing` int(11) NOT NULL,
  `id_charging_station` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  `start_date_pricing` date DEFAULT NULL,
  `end_date_pricing` date DEFAULT NULL,
  PRIMARY KEY (`id_pricing`),
  KEY `FK_proposer` (`id_type_pricing`),
  KEY `FK_pricinger` (`id_charging_station`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.pricing : ~0 rows (environ)
INSERT INTO `pricing` (`id_pricing`, `id_type_pricing`, `id_charging_station`, `price`, `start_date_pricing`, `end_date_pricing`) VALUES
	(1, 1, 1, 0.5, '2025-02-01', NULL),
	(2, 2, 2, 1.01, '2025-02-18', NULL),
	(3, 2, 3, 0.1, '2025-02-18', NULL),
	(4, 3, 10, 5.5, '2025-01-17', NULL),
	(5, 2, 9, 4.5, '2025-01-17', NULL),
	(6, 1, 8, 3.5, '2025-01-17', NULL),
	(7, 3, 7, 6.5, '2024-04-17', NULL),
	(8, 3, 6, 7.5, '2024-03-17', NULL),
	(9, 2, 5, 5.5, '2024-03-14', NULL),
	(10, 1, 4, 3.5, '2024-02-14', NULL);

-- Listage de la structure de table wattelse. pricing_type
CREATE TABLE IF NOT EXISTS `pricing_type` (
  `id_type_pricing` int(11) NOT NULL AUTO_INCREMENT,
  `type_pricing` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_type_pricing`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.pricing_type : ~0 rows (environ)

-- Listage de la structure de table wattelse. session
CREATE TABLE IF NOT EXISTS `session` (
  `id_session` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(50) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_session`),
  UNIQUE KEY `id_user` (`id_user`),
  KEY `FK_session_user` (`id_user`),
  CONSTRAINT `FK_session_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Listage des données de la table wattelse.session : ~0 rows (environ)

-- Listage de la structure de table wattelse. station_closing_type
CREATE TABLE IF NOT EXISTS `station_closing_type` (
  `id_station_closing_type` int(11) NOT NULL AUTO_INCREMENT,
  `station_closing_type` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_station_closing_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.station_closing_type : ~0 rows (environ)

-- Listage de la structure de table wattelse. transaction
CREATE TABLE IF NOT EXISTS `transaction` (
  `id_transaction` int(11) NOT NULL AUTO_INCREMENT,
  `id_payment` int(11) NOT NULL,
  `id_cancellation_type` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `id_charging_station` int(11) NOT NULL,
  `id_payment_refuse_type` int(11) DEFAULT NULL,
  `registration_reservation_date` timestamp DEFAULT NULL,
  `reservation_date` datetime DEFAULT NULL,
  `reservation_duration` int(11) DEFAULT NULL,
  `start_date_charging` timestamp DEFAULT NULL,
  `end_date_charging` timestamp DEFAULT NULL,
  `date_payment` timestamp DEFAULT NULL,
  `confirmation_date_reservation` timestamp DEFAULT NULL,
  `refuse_date_reservation` timestamp DEFAULT NULL,
  `reservation_cancellation_date` timestamp DEFAULT NULL,
  `consume_quantity` float DEFAULT NULL,
  `monetary_amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_transaction`),
  KEY `FK_brancher` (`id_charging_station`),
  KEY `FK_charger` (`id_user`),
  KEY `FK_motiver` (`id_cancellation_type`),
  KEY `FK_payer` (`id_payment`),
  KEY `FK_refuser` (`id_payment_refuse_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.transaction : ~0 rows (environ)

-- Listage de la structure de table wattelse. unavailability
CREATE TABLE IF NOT EXISTS `unavailability` (
  `id_unavailability` int(11) NOT NULL AUTO_INCREMENT,
  `id_unavailability_type` int(11) NOT NULL,
  `id_charging_station` int(11) NOT NULL,
  `start_date_unavailability` date DEFAULT NULL,
  `end_date_unavailability` date DEFAULT NULL,
  PRIMARY KEY (`id_unavailability`),
  KEY `FK_fermer` (`id_charging_station`),
  KEY `FK_justifier` (`id_unavailability_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.unavailability : ~0 rows (environ)
INSERT INTO `unavailability` (`id_unavailability`, `id_unavailability_type`, `id_charging_station`, `start_date_unavailability`, `end_date_unavailability`) VALUES
	(1, 1, 3, '2025-02-19', '2025-02-23'),
	(2, 2, 10, '2025-02-19', '2025-02-20'),
	(3, 3, 9, '2025-03-17', '2025-03-18'),
	(4, 4, 8, '2025-07-17', '2025-07-18'),
	(5, 5, 7, '2024-04-17', '2024-04-18'),
	(6, 6, 6, '2024-05-17', '2024-05-18'),
	(7, 7, 5, '2024-06-14', '2024-06-15'),
	(8, 1, 4, '2024-02-14', '2024-02-15');

-- Listage de la structure de table wattelse. unavailability_type
CREATE TABLE IF NOT EXISTS `unavailability_type` (
  `id_unavailability_type` int(11) NOT NULL AUTO_INCREMENT,
  `unavailability_type` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_unavailability_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.unavailability_type : ~0 rows (environ)

-- Listage de la structure de table wattelse. user
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `id_city` int(11) NOT NULL,
  `id_label_closing_account_user` int(11) DEFAULT NULL,
  `inscription_date_user` timestamp DEFAULT NULL,
  `firstname_user` varchar(254) DEFAULT NULL,
  `lastname_user` varchar(254) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone_number` varchar(254) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL,
  `closing_date_account` timestamp DEFAULT NULL,
  `address_user` varchar(254) DEFAULT NULL,
  `role` varchar(255) DEFAULT '"USER"',
  PRIMARY KEY (`id_user`),
  KEY `FK_demeurer` (`id_city`),
  KEY `FK_quitter` (`id_label_closing_account_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.user : ~0 rows (environ)
INSERT INTO `user` (`id_user`, `id_city`, `id_label_closing_account_user`, `inscription_date_user`, `firstname_user`, `lastname_user`, `birthdate`, `phone_number`, `email`, `password`, `closing_date_account`, `address_user`, `role`) VALUES
	(1, 1, NULL, '2025-02-11 00:00:00', 'Admin', 'administrator', '2025-02-12', '123456789', 'wattelseinc@proton.me', '-1402147925', NULL, NULL, 'ADMIN'),
	(2, 6, NULL, '2025-02-20 13:07:39', 'Jean', 'Tulliez', '1999-08-05', NULL, 'jt@gmail.com', '-677490036', NULL, '25 rue charles', 'USER'),
	(3, 7, NULL, '2025-02-20 13:09:23', 'Jules', 'Tulliez', '1999-08-05', NULL, 'Mnt@gmail.com', '-677490036', NULL, '25 rue charles', 'USER'),
	(4, 8, NULL, '2025-02-20 13:10:12', 'Cyrine', 'Tulliez', '1999-08-05', NULL, 'cyrine@gmail.com', '-677490036', NULL, '25 rue charles', 'USER'),
	(5, 9, NULL, '2025-02-20 13:10:51', 'Arvidas', 'Tulliez', '1999-08-05', NULL, 'arvidas@gmail.com', '-677490036', NULL, '25 rue charles', 'USER'),
	(6, 10, NULL, '2025-02-20 13:12:08', 'Sara', 'Tulliez', '1999-08-05', NULL, 'sara@gmail.com', '-677490036', NULL, '25 rue charles', 'USER');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
