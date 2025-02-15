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

-- Listage de la structure de table wattelse. brand_car
CREATE TABLE IF NOT EXISTS `brand_car` (
  `id_brand` int(11) NOT NULL AUTO_INCREMENT,
  `brand_label` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_brand`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.brand_car : ~0 rows (environ)


-- Listage de la structure de table wattelse. cancellation_type
CREATE TABLE IF NOT EXISTS `cancellation_type` (
  `id_cancellation_type` int(11) NOT NULL AUTO_INCREMENT,
  `cancellation_type_` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_cancellation_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.cancellation_type : ~0 rows (environ)

-- Listage de la structure de table wattelse. car
CREATE TABLE IF NOT EXISTS `car` (
  `id_car` int(11) NOT NULL AUTO_INCREMENT,
  `id_model_car` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `license_plate_number` varchar(254) DEFAULT NULL,
  `registration_date_car` timestamp DEFAULT NULL,
  `remove_date_car` timestamp DEFAULT NULL,
  `max_electric_power` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_car`),
  KEY `FK_conduire` (`id_user`),
  KEY `FK_definir` (`id_model_car`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.car : ~0 rows (environ)

-- Listage de la structure de table wattelse. car_withdrawal_reason
CREATE TABLE IF NOT EXISTS `car_withdrawal_reason` (
  `id_retraction_vehicule` int(11) NOT NULL AUTO_INCREMENT,
  `id_car` int(11) NOT NULL,
  `retraction_vehicule` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_retraction_vehicule`),
  KEY `FK_retirer` (`id_car`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.car_withdrawal_reason : ~0 rows (environ)

-- Listage de la structure de table wattelse. charging_station
CREATE TABLE IF NOT EXISTS `charging_station` (
  `id_charging_station` int(11) NOT NULL AUTO_INCREMENT,
  `id_station_closing_type` int(11) DEFAULT NULL,
  `id_plug_type` int(11) NOT NULL,
  `id_city` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `power_charging_station` int(11) DEFAULT NULL,
  `registration_station_date` datetime DEFAULT NULL,
  `closing_station_date` datetime DEFAULT NULL,
  `address_charging_station` varchar(255) DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `emergency_phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_charging_station`),
  KEY `FK_disposer` (`id_plug_type`),
  KEY `FK_expliquer` (`id_station_closing_type`),
  KEY `FK_installer` (`id_user`),
  KEY `FK_localiser` (`id_city`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.charging_station : ~0 rows (environ)

-- Listage de la structure de table wattelse. city
CREATE TABLE IF NOT EXISTS `city` (
  `id_city` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(254) DEFAULT NULL,
  `postal_code` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_city`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.city : ~0 rows (environ)
INSERT INTO `city` (`id_city`, `city`, `postal_code`) VALUES
	(1, 'Paris', '75001');

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
-- Listage des données de la table wattelse.model_car : ~71 rows (environ)
INSERT INTO `model_car` (`id_model_car`, `id_plug_type`, `id_brand`, `car_model_label`) VALUES
	(1, 1, 1, 'MODEL S'),
	(2, 2, 1, 'MODEL 3'),
	(3, 3, 1, 'MODEL X'),
	(4, 4, 1, 'MODEL Y'),
	(5, 5, 1, 'CYBERTRUCK'),
	(6, 6, 1, 'SEMI'),
	(7, 7, 2, 'R1T'),
	(8, 8, 2, 'R1S'),
	(9, 9, 3, 'AIR'),
	(10, 1, 4, 'M_BYTE'),
	(11, 2, 5, 'ES8'),
	(12, 3, 5, 'ES6'),
	(13, 4, 5, 'EC6'),
	(14, 5, 5, 'ET7'),
	(15, 6, 6, '1'),
	(16, 7, 6, '2'),
	(17, 8, 6, '3'),
	(18, 9, 7, 'OCEAN'),
	(19, 1, 7, 'EMOTION'),
	(20, 2, 8, 'B1'),
	(21, 3, 8, 'B2'),
	(22, 4, 9, 'ENDURANCE'),
	(23, 5, 10, 'FF 91'),
	(24, 6, 11, 'EV'),
	(25, 7, 12, 'ETRON'),
	(26, 8, 12, 'ETRON GT'),
	(27, 9, 12, 'Q4 ETRON'),
	(28, 1, 13, 'I3'),
	(29, 2, 13, 'I4'),
	(30, 3, 13, 'IX3'),
	(31, 4, 13, 'IX'),
	(32, 5, 14, 'ID 3'),
	(33, 6, 14, 'ID 4'),
	(34, 7, 14, 'ID BUZZ'),
	(35, 8, 15, 'EQC'),
	(36, 9, 15, 'EQS'),
	(37, 1, 15, 'EQB'),
	(38, 2, 16, 'TAYCAN'),
	(39, 3, 16, 'TAYCAN CROSS TURISMO'),
	(40, 4, 17, 'MUSTANG MACH E'),
	(41, 5, 17, 'F 150 LIGHTNING'),
	(42, 6, 18, 'BOLT EV'),
	(43, 7, 18, 'BOLT EUV'),
	(44, 8, 19, 'IONIQ 5'),
	(45, 9, 19, 'KONA ELECTRIC'),
	(46, 1, 20, 'SOUL EV'),
	(47, 2, 20, 'NIRO EV'),
	(48, 3, 21, 'LEAF'),
	(49, 4, 21, 'ARIYA'),
	(50, 5, 22, 'XC40 RECHARGE'),
	(51, 6, 22, 'C40 RECHARGE'),
	(52, 7, 23, 'E 208'),
	(53, 8, 23, 'E 2008'),
	(54, 9, 24, 'ZOE'),
	(55, 1, 24, 'MEGANE E TECH ELECTRIC'),
	(56, 2, 25, 'I PACE'),
	(57, 3, 26, 'DEFENDER EV'),
	(58, 4, 26, 'EV'),
	(59, 5, 27, 'MINI ELECTRIC'),
	(60, 6, 28, 'MX 30'),
	(61, 7, 29, '500 ELECTRIC'),
	(62, 8, 30, 'E'),
	(63, 9, 31, 'BZ4X'),
	(64, 1, 32, 'EQ FORTWO'),
	(65, 2, 33, 'SOLTERRA'),
	(66, 3, 34, 'TANG EV'),
	(67, 4, 34, 'QIN EV'),
	(68, 5, 34, 'E6'),
	(69, 6, 35, 'P7'),
	(70, 7, 35, 'XPENG'),
	(71, 10, 36, 'Autre'),
	(72, 9, 36, 'Autre'),
	(73, 8, 36, 'Autre'),
	(74, 7, 36, 'Autre'),
	(75, 6, 36, 'Autre'),
	(76, 5, 36, 'Autre'),
	(77, 4, 36, 'Autre'),
	(78, 3, 36, 'Autre'),
	(79, 2, 36, 'Autre'),
	(80, 1, 36, 'Autre');

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

-- Listage de la structure de table wattelse. payment
CREATE TABLE IF NOT EXISTS `payment` (
  `id_payment` int(11) NOT NULL AUTO_INCREMENT,
  `id_bank_account` int(11) DEFAULT NULL,
  `id_credit_card` int(11) DEFAULT NULL,
  `payement_date` datetime DEFAULT NULL,
  `payement_amount` int(11) DEFAULT NULL,
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
  `price` int(11) DEFAULT NULL,
  `start_date_pricing` date DEFAULT NULL,
  `end_date_pricing` date DEFAULT NULL,
  PRIMARY KEY (`id_pricing`),
  KEY `FK_proposer` (`id_type_pricing`),
  KEY `FK_pricinger` (`id_charging_station`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.pricing : ~0 rows (environ)

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
  `id_car` int(11) NOT NULL,
  `id_bank_account` int(11) DEFAULT NULL,
  `id_credit_card` int(11) DEFAULT NULL,
  `id_charging_station` int(11) NOT NULL,
  `id_payment_refuse_type` int(11) DEFAULT NULL,
  `registration_reservation_date` datetime DEFAULT NULL,
  `reservation_date` datetime DEFAULT NULL,
  `reservation_duration` int(11) DEFAULT NULL,
  `start_date_charging` datetime DEFAULT NULL,
  `end_date_charging` datetime DEFAULT NULL,
  `date_payment` datetime DEFAULT NULL,
  `confirmation_date_reservation` datetime DEFAULT NULL,
  `refuse_date_reservation` datetime DEFAULT NULL,
  `cancellation_date` datetime DEFAULT NULL,
  `consume_quantity_power` int(11) DEFAULT NULL,
  `monetary_amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_transaction`),
  KEY `FK_brancher` (`id_charging_station`),
  KEY `FK_charger` (`id_car`),
  KEY `FK_motiver` (`id_cancellation_type`),
  KEY `FK_payer` (`id_payment`),
  KEY `FK_prelever` (`id_bank_account`),
  KEY `FK_refuser` (`id_payment_refuse_type`),
  KEY `FK_utiliser` (`id_credit_card`)
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
	(1, 1, NULL, '2025-02-11', 'Admin', 'administrator', '2025-02-12', '123456789', 'wattelseinc@proton.me', '-1402147925', NULL, NULL, 'ADMIN');


/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
