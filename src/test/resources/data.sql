-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: utopia-airlines.cmnyotwgbsoe.us-east-2.rds.amazonaws.com    Database: utopiadb
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */

SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE tbl_tickets;
TRUNCATE TABLE tbl_travelers;
TRUNCATE TABLE tbl_payments;
TRUNCATE TABLE tbl_accounts;
TRUNCATE TABLE tbl_account_roles;
TRUNCATE TABLE tbl_flights;
TRUNCATE TABLE tbl_aircraft;
TRUNCATE TABLE tbl_aircraft_type;
TRUNCATE TABLE tbl_airports;
SET FOREIGN_KEY_CHECKS=1;


/* 
INSERT INTO `tbl_accounts` VALUES (-1,1,'defaultusername','null@null.com','null', '2021-04-03'),(1,1,'userTest332','user@user.com','user', '2020-03-23'),(2,2,'admin','admin@admin.com','admin','2020-03-23'),(5,1,'testuser','testuser1@example.com','$2a$10$0Z/BsKo5BwnCw3buTrvsd.dMm.DUEnYA1a79fgoK4ARni5j6BY41i',NULL),(20,3,NULL,NULL,NULL,'2021-03-31'),(23,2,'testadmin','testadmin1@example.com','$2a$10$t0kSH7M6pB4KuDkut.bLC.x5uPr01HvFLhw8fPfyzabuwYGe5VoQS',NULL),(27,1,'user1','user1@gmail.com','$2a$10$DZq.YksdkZlIbnbvln1qYuFwmVE5CNj2seRelgcDIiYf923CzOoeC',NULL),(28,2,'admin1',NULL,'$2a$10$n5PnGLyrY5tKOoXg16p2CeAKCMU2QFy2U7LDQUa9KGfsZho1j6q7a',NULL),(29,1,'user2','user2@gmail.com','$2a$10$LPDXBT9EcG8/JynhGLdAMu83hEYuW7lRsEtpGkbStZNqYdsSjbdWy',NULL),(39,2,'admin2',NULL,'$2a$10$lte1VavM4Q68jGTEah0U0.WwjyVy1./OTsY7uQ4zPE/vvW6ePf2jS',NULL);
*/

INSERT INTO `tbl_airports` 
VALUES (1,30,'Afognak Lake','01A','Active'),(2,10056,'Kodiak Island','A43','Active'),(3,10721,'Boston','BOS','Active'),(4,12478,'New York','JFK','Active'),(5,12953,'New York','LGA','Active'),(6,10397,'Atlanta','ATL','Active');

INSERT INTO `tbl_aircraft_type` (`aircraft_type_id`, `aircraft_type_name`, `seat_maximum`, `manufacturer`) 
VALUES (-1,'Test Aircraft Type',100,'Test Manufacturer'),(40,'DEHAVILLAND DHC2',100,'DEHAVILLAND OF CANADA'),(673,'EMBRAER-175',76,'EMBRAER'),(723,'A220-100 BD-500-1A',109,'AIRBUS');

INSERT INTO `tbl_aircraft` (`aircraft_id`, `aircraft_type_id`, `seat_count`, `first_class_count`, `second_class_count`, `third_class_count`, `aircraft_status`) 
VALUES (-1,40,100,25,25,25,'Inactive'),(1,40,100,10,5,20,'Active'),(2,673,76,20,26,30,'Active'),(3,723,109,30,40,39,'Active'),(4,673,73,20,30,20,'Active'),(53,40,50,5,5,5,'Active'),(54,723,60,6,6,6,'Active'),(57,723,25,2,4,3,'Active'),(58,723,8,2,3,2,'Active'),(59,673,17,1,2,2,'Active');

INSERT INTO `tbl_account_roles` 
VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN'),(3,'DEACTIVATED');

INSERT INTO `tbl_accounts` (`account_number`, `role_id`, `username`, `email`, `password`, `date_created`) 
VALUES (-1,1,'defaultusername','null@null.com','null', '2021-04-03'),(1,1,'userTest332','user@user.com','user', '2020-03-23'),(2,2,'admin','admin@admin.com','admin','2020-03-23'),(5,1,'testuser','testuser1@example.com','$2a$10$0Z/BsKo5BwnCw3buTrvsd.dMm.DUEnYA1a79fgoK4ARni5j6BY41i',NULL),(20,3,NULL,NULL,NULL,'2021-03-31'),(23,2,'testadmin','testadmin1@example.com','$2a$10$t0kSH7M6pB4KuDkut.bLC.x5uPr01HvFLhw8fPfyzabuwYGe5VoQS',NULL),(27,1,'user1','user1@gmail.com','$2a$10$DZq.YksdkZlIbnbvln1qYuFwmVE5CNj2seRelgcDIiYf923CzOoeC',NULL),(28,2,'admin1',NULL,'$2a$10$n5PnGLyrY5tKOoXg16p2CeAKCMU2QFy2U7LDQUa9KGfsZho1j6q7a',NULL),(29,1,'user2','user2@gmail.com','$2a$10$LPDXBT9EcG8/JynhGLdAMu83hEYuW7lRsEtpGkbStZNqYdsSjbdWy',NULL),(39,2,'admin2',NULL,'$2a$10$lte1VavM4Q68jGTEah0U0.WwjyVy1./OTsY7uQ4zPE/vvW6ePf2jS',NULL);

INSERT INTO `tbl_payments` (`payment_id`, `account_number`, `date_processed`) VALUES (1,1,'2021-03-29'),(2,1,'2021-04-07');

INSERT INTO `tbl_travelers` (`traveler_id`, `account_number`, `first_name`, `dob`, `middle_name`, `last_name`, `gender`, `known_traveler_number`) 
VALUES (1,1,'firstName','2000-05-13','middleName','lastName','M','4820'),(2,1,'firstName2','2002-02-13','toes','lastName2','F',NULL);

INSERT INTO `tbl_flights` (`flight_no`, `flight_gate`, `aircraft_id`, `airport_id_dep`, `airport_id_arr`, `base_price`, `departure`, `arrival`, `status`)
VALUES (1,'2B',1,1,2,50,'2020-01-01 ','2020-01-01 ','Completed'),(2,'3C',2,3,4,150,'2020-10-01 ','2020-10-01 ','Completed'),(3,'4D',3,4,3,150,'2020-07-12 ','2020-07-12 ','Completed'),(4,'5E',3,5,6,100,'2020-12-28 ','2020-12-28 ','Completed'),(5,'17B',1,1,2,50,'2020-01-01 ','2020-01-01 ','Completed'),(12,'1A',3,1,4,100,'2021-04-01 ','2021-04-02 ','Ready'),(15,'1a',2,4,2,100,'2021-04-03 ','2021-04-04 ','Ready'),(47,'12A',2,2,1,30,'2021-04-06 ','2021-04-07 ','Ready'),(48,'12B',53,2,5,20,'2021-04-08 ','2021-04-09 ','Ready'),(49,'12C',3,2,3,20,'2021-04-10 ','2021-04-11 ','Ready'),(50,'12D',3,4,6,30,'2021-04-16 ','2021-04-17 ','Ready'),(51,'12D',53,3,4,50,'2021-04-15 ','2021-04-16 ','Ready'),(52,'testingfordan',2,1,2,30,'2021-04-03 ','2021-04-09 ','Ready'),(54,'testing records',2,3,1,30,'2021-04-08 ','2021-04-09 ','Ready'),(55,'12D',2,1,2,30,'2021-04-07 ','2021-04-08 ','Ready'),(56,'391D',1,2,1,150,'2021-04-20 ','2021-04-21 ','Ready'),(57,'43B',2,2,1,150,'2021-04-22 ','2021-04-25 ','Ready'),(58,'1A',3,1,3,100,'2021-04-01 ','2021-04-02 ','Ready'),(59,'1a',2,3,2,100,'2021-04-03 ','2021-04-04 ','Ready'),(73,'321A',2,2,3,15,'2021-04-10 ','2021-04-11 ','Ready');

INSERT INTO `tbl_tickets` (`ticket_no`, `flight_no`, `traveler_id`, `confirmation_code`, `ticket_price`, `date_issued`, `payment_id`) 
VALUES (1,2,1,123,5,'2021-03-21',1),(2,1,1,1,2,'2021-03-29',1);



