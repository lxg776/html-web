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
-- Table structure for table `s_executor`
--

DROP TABLE IF EXISTS `s_executor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `s_executor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(50) NOT NULL,
  `c_type` varchar(100) DEFAULT NULL,
  `link_url` varchar(100) DEFAULT NULL,
  `c_describe` varchar(100) DEFAULT NULL,
  `config_jsontext` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `s_executor`
--

LOCK TABLES `s_executor` WRITE;
/*!40000 ALTER TABLE `s_executor` DISABLE KEYS */;
INSERT INTO `s_executor` VALUES (1,'jx_gov',NULL,'http://www.jingxi.gov.cn/index.php?m=content&c=index&a=lists&catid=22','抓取靖西政府网数据','{\r\n    \"site\": {\r\n        \"domain\": \"http://www.jingxi.gov.cn/index.php?m=content&c=index&a=lists&catid=22\",\r\n        \"headers\": {\r\n            \"User-Agent\": \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36\",\r\n            \"authorization\": \"Your own authorization here.\"\r\n        },\r\n        \"retryTimes\": 3,\r\n        \"sleepTime\": 10\r\n    },\r\n    \"imgFolder\": \"D:/wtf/spider_images/images_hd/\",\r\n    \"thumbnail\": \"D:/wtf/spider_images/images_thum/\",\r\n    \"pictureSite\": \"http://127.0.0.1:8080/images01/images_hd/\",\r\n    \"thumbnailSite\": \"http://127.0.0.1:8080/images01/thumbnail/\",\r\n    \"columnId\": \"2\"\r\n}\r\n'),(2,'superMan',NULL,'http://www.baidu.com','测试测试','');
/*!40000 ALTER TABLE `s_executor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-01 16:02:40
