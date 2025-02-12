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
  `iban` bigint(20) NOT NULL,
  `cardholder_name` varchar(254) DEFAULT NULL,
  `bic_swift` bigint(20) DEFAULT NULL,
  `start_date_registration_account` datetime DEFAULT NULL,
  `closing_date_bank_account` datetime DEFAULT NULL,
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
  `license_plate_number` int(11) DEFAULT NULL,
  `registration_date_car` datetime DEFAULT NULL,
  `remove_date_car` datetime DEFAULT NULL,
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
  `address_charging_station` int(11) DEFAULT NULL,
  `longitude` int(11) DEFAULT NULL,
  `latitude` int(11) DEFAULT NULL,
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
  `expiration_date` datetime DEFAULT NULL,
  `cvv_number` int(11) DEFAULT NULL,
  `registration_date_carte` datetime DEFAULT NULL,
  `withdrawal_date_card` datetime DEFAULT NULL,
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
  `id_model_car` int(11) NOT NULL,
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
  `start_hour` datetime DEFAULT NULL,
  `end_hour` datetime DEFAULT NULL,
  `start_validity_date_opening_hour` datetime DEFAULT NULL,
  `end_validity_date_opening_hour` datetime DEFAULT NULL,
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
  `start_date_pricing` datetime DEFAULT NULL,
  `end_date_pricing` datetime DEFAULT NULL,
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
  `start_date_unavailability` datetime DEFAULT NULL,
  `end_date_unavailability` datetime DEFAULT NULL,
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
  `inscription_date_user` datetime DEFAULT NULL,
  `firstname_user` varchar(254) DEFAULT NULL,
  `lastname_user` varchar(254) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `phone_number` varchar(254) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL,
  `closing_date_account` datetime DEFAULT NULL,
  `address_user` varchar(254) DEFAULT NULL,
  `role` varchar(255) DEFAULT '"USER"',
  PRIMARY KEY (`id_user`),
  KEY `FK_demeurer` (`id_city`),
  KEY `FK_quitter` (`id_label_closing_account_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Listage des données de la table wattelse.user : ~0 rows (environ)
INSERT INTO `user` (`id_user`, `id_city`, `id_label_closing_account_user`, `inscription_date_user`, `firstname_user`, `lastname_user`, `birthdate`, `phone_number`, `email`, `password`, `closing_date_account`, `address_user`, `role`) VALUES
	(1, 1, NULL, '2025-02-11', 'Admin', 'administrator', '2025-02-12', '123456789', 'wattelseinc@proton.me', 'azerty', NULL, NULL, 'ADMIN');


/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
