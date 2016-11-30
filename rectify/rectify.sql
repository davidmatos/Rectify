-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rectify
-- ------------------------------------------------------
-- Server version	5.5.53

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `configuration_name` varchar(255) DEFAULT NULL,
  `configuration_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (179,'webappname','Wordpress'),(180,'webappurl','http://localhost/wordpress'),(181,'httplocalport','8888'),(182,'httpremotehost','localhost'),(183,'httpremoteport','80'),(184,'dblocalport','3306'),(185,'dbremotehost','localhost'),(186,'dbremoteport','6789');
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kb_db_op`
--

DROP TABLE IF EXISTS `kb_db_op`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kb_db_op` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `query` varchar(1024) DEFAULT NULL,
  `id_kb_http_request` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kb_http_request_idx` (`id_kb_http_request`),
  CONSTRAINT `id_kb_http_request` FOREIGN KEY (`id_kb_http_request`) REFERENCES `kb_http_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kb_db_op`
--

LOCK TABLES `kb_db_op` WRITE;
/*!40000 ALTER TABLE `kb_db_op` DISABLE KEYS */;
/*!40000 ALTER TABLE `kb_db_op` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kb_db_op_parts`
--

DROP TABLE IF EXISTS `kb_db_op_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kb_db_op_parts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `part_key` varchar(255) DEFAULT NULL,
  `value` varchar(1024) DEFAULT NULL,
  `id_kb_db_op` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kb_db_op` (`id_kb_db_op`),
  CONSTRAINT `kb_db_op_parts_ibfk_1` FOREIGN KEY (`id_kb_db_op`) REFERENCES `kb_db_op` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kb_db_op_parts`
--

LOCK TABLES `kb_db_op_parts` WRITE;
/*!40000 ALTER TABLE `kb_db_op_parts` DISABLE KEYS */;
/*!40000 ALTER TABLE `kb_db_op_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kb_http_request`
--

DROP TABLE IF EXISTS `kb_http_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kb_http_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `request` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kb_http_request`
--

LOCK TABLES `kb_http_request` WRITE;
/*!40000 ALTER TABLE `kb_http_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `kb_http_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kb_http_request_parts`
--

DROP TABLE IF EXISTS `kb_http_request_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kb_http_request_parts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `part_key` varchar(255) DEFAULT NULL,
  `value` varchar(1024) DEFAULT NULL,
  `id_kb_http_request` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kb_http_request` (`id_kb_http_request`),
  CONSTRAINT `kb_http_request_parts_ibfk_1` FOREIGN KEY (`id_kb_http_request`) REFERENCES `kb_http_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kb_http_request_parts`
--

LOCK TABLES `kb_http_request_parts` WRITE;
/*!40000 ALTER TABLE `kb_http_request_parts` DISABLE KEYS */;
/*!40000 ALTER TABLE `kb_http_request_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kb_http_response`
--

DROP TABLE IF EXISTS `kb_http_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kb_http_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_kb_http_request` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kb_http_request` (`id_kb_http_request`),
  CONSTRAINT `kb_http_response_ibfk_1` FOREIGN KEY (`id_kb_http_request`) REFERENCES `kb_http_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kb_http_response`
--

LOCK TABLES `kb_http_response` WRITE;
/*!40000 ALTER TABLE `kb_http_response` DISABLE KEYS */;
/*!40000 ALTER TABLE `kb_http_response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kb_http_response_parts`
--

DROP TABLE IF EXISTS `kb_http_response_parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kb_http_response_parts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `part_key` varchar(255) DEFAULT NULL,
  `value` varchar(1024) DEFAULT NULL,
  `id_kb_http_response` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_kb_http_response` (`id_kb_http_response`),
  CONSTRAINT `kb_http_response_parts_ibfk_1` FOREIGN KEY (`id_kb_http_response`) REFERENCES `kb_http_response` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kb_http_response_parts`
--

LOCK TABLES `kb_http_response_parts` WRITE;
/*!40000 ALTER TABLE `kb_http_response_parts` DISABLE KEYS */;
/*!40000 ALTER TABLE `kb_http_response_parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_db_operation`
--

DROP TABLE IF EXISTS `log_db_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_db_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `request` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_db_operation`
--

LOCK TABLES `log_db_operation` WRITE;
/*!40000 ALTER TABLE `log_db_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_db_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_db_sub_operation`
--

DROP TABLE IF EXISTS `log_db_sub_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_db_sub_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pk` int(11) DEFAULT NULL,
  `id_log_db_operation` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_log_db_operation` (`id_log_db_operation`),
  CONSTRAINT `log_db_sub_operation_ibfk_1` FOREIGN KEY (`id_log_db_operation`) REFERENCES `log_db_operation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_db_sub_operation`
--

LOCK TABLES `log_db_sub_operation` WRITE;
/*!40000 ALTER TABLE `log_db_sub_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_db_sub_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_http_request`
--

DROP TABLE IF EXISTS `log_http_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_http_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `request` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_http_request`
--

LOCK TABLES `log_http_request` WRITE;
/*!40000 ALTER TABLE `log_http_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_http_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_http_response`
--

DROP TABLE IF EXISTS `log_http_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_http_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `response` varchar(10000) DEFAULT NULL,
  `id_log_http_request` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_log_http_request` (`id_log_http_request`),
  CONSTRAINT `log_http_response_ibfk_1` FOREIGN KEY (`id_log_http_request`) REFERENCES `log_http_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_http_response`
--

LOCK TABLES `log_http_response` WRITE;
/*!40000 ALTER TABLE `log_http_response` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_http_response` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-29 19:09:55
