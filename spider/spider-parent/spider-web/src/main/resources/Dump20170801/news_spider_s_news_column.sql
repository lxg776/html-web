-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: news_spider
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `s_news_column`
--

DROP TABLE IF EXISTS `s_news_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_news_column` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(50) NOT NULL,
  `c_describe` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_news_column`
--

LOCK TABLES `s_news_column` WRITE;
/*!40000 ALTER TABLE `s_news_column` DISABLE KEYS */;
INSERT INTO `s_news_column` VALUES (1,'靖西政府网','wtf110'),(2,'我是10s先生','2017-06-24 16:52:35'),(3,'我是10s先生','2017-06-24 16:52:40'),(4,'我是10s先生','2017-06-24 16:52:50'),(5,'我是10s先生','2017-06-24 16:53:00'),(6,'我是10s先生','2017-06-24 16:53:10'),(7,'我是10s先生','2017-06-24 16:53:20'),(8,'我是10s先生','2017-06-24 16:53:30'),(9,'我是10s先生','2017-06-24 16:53:40'),(10,'我是10s先生','2017-06-24 16:53:50'),(11,'我是10s先生','2017-06-24 16:54:00'),(12,'我是10s先生','2017-06-24 16:54:10'),(13,'我是10s先生','2017-06-24 16:54:20'),(14,'我是10s先生','2017-06-24 16:54:30'),(15,'我是10s先生','2017-06-24 16:55:50'),(16,'我是10s先生','2017-06-24 16:56:00'),(17,'我是10s先生','2017-06-24 16:56:10'),(18,'我是10s先生','2017-06-24 16:56:20'),(19,'我是10s先生','2017-06-24 16:56:30'),(20,'我是10s先生','2017-06-24 16:56:40'),(21,'我是10s先生','2017-06-24 16:56:50'),(22,'我是10s先生','2017-06-24 17:00:54'),(23,'我是10s先生','2017-06-24 17:01:00'),(24,'我是10s先生','2017-06-24 17:01:10'),(25,'我是10s先生','2017-06-24 17:01:20');
/*!40000 ALTER TABLE `s_news_column` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-01 16:02:46
