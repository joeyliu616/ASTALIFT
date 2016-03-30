-- MySQL dump 10.13  Distrib 5.7.9, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: astalift
-- ------------------------------------------------------
-- Server version	5.7.10

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
-- Table structure for table `t_delivery`
--

DROP TABLE IF EXISTS `t_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `delivery_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_delivery`
--

LOCK TABLES `t_delivery` WRITE;
/*!40000 ALTER TABLE `t_delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `current_status` int(11) NOT NULL,
  `order_no` varchar(255) NOT NULL,
  `receive_address` varchar(255) NOT NULL,
  `receive_contact` varchar(255) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `total` float NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `delivery_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fjie9ovlyccw6819bahkq6b59` (`order_no`),
  KEY `FK_fiqelifk8guglmwksr8v9rjob` (`delivery_id`),
  CONSTRAINT `FK_fiqelifk8guglmwksr8v9rjob` FOREIGN KEY (`delivery_id`) REFERENCES `t_delivery` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_item`
--

DROP TABLE IF EXISTS `t_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `discount` float NOT NULL,
  `price` float NOT NULL,
  `product_id` int(11) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tr4wwliho4mtheslf3eugb9km` (`order_id`),
  CONSTRAINT `FK_tr4wwliho4mtheslf3eugb9km` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_item`
--

LOCK TABLES `t_order_item` WRITE;
/*!40000 ALTER TABLE `t_order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order_status_history`
--

DROP TABLE IF EXISTS `t_order_status_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order_status_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `c_desc` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ft057f7hmj119sxb4brpydyr3` (`order_id`),
  CONSTRAINT `FK_ft057f7hmj119sxb4brpydyr3` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order_status_history`
--

LOCK TABLES `t_order_status_history` WRITE;
/*!40000 ALTER TABLE `t_order_status_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_order_status_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product`
--

DROP TABLE IF EXISTS `t_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `measure` int(11) DEFAULT NULL,
  `price` float NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `title_image_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gny2s7xxvfvd8bqxydaarjqt1` (`title_image_id`),
  CONSTRAINT `FK_gny2s7xxvfvd8bqxydaarjqt1` FOREIGN KEY (`title_image_id`) REFERENCES `t_product_title_image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product`
--

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
INSERT INTO `t_product` VALUES (1,1,NULL,1,680,'ASTALIFT艾诗缇晶粹沁水凝露','面霜',1),(2,1,NULL,1,480,'ASTALIFT艾诗缇赋活修护晚霜','晚霜',2),(3,1,NULL,1,300,'ASTALIFT艾诗缇焕颜隔离霜','隔离霜',3),(4,1,NULL,1,360,'ASTALIFT艾诗缇净皙钻白化妆水','化妆水',4),(5,1,NULL,1,360,'ASTALIFT艾诗缇Lunamer月之清辉焕采亮肤液','亮肤液',5),(6,1,NULL,1,130,'ASTALIFT艾诗缇Lunamer月之清辉焕采亮肤卸妆油','卸妆油',6);
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product_detail_image`
--

DROP TABLE IF EXISTS `t_product_detail_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_detail_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_guukpa51ms42f58s5ry22bjso` (`product_id`),
  CONSTRAINT `FK_guukpa51ms42f58s5ry22bjso` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product_detail_image`
--

LOCK TABLES `t_product_detail_image` WRITE;
/*!40000 ALTER TABLE `t_product_detail_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_product_detail_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product_thumbnail_image`
--

DROP TABLE IF EXISTS `t_product_thumbnail_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_thumbnail_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dv6y07frl9un36x0iummmi2xk` (`product_id`),
  CONSTRAINT `FK_dv6y07frl9un36x0iummmi2xk` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product_thumbnail_image`
--

LOCK TABLES `t_product_thumbnail_image` WRITE;
/*!40000 ALTER TABLE `t_product_thumbnail_image` DISABLE KEYS */;
INSERT INTO `t_product_thumbnail_image` VALUES (1,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/292/524401b4410c4.jpg',1),(2,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/292/524401c30cba8.jpg',1),(3,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/292/524401c6af95f.jpg',1),(4,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/292/524401c53d10d.jpg',1),(5,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/292/524401c3ef85d.jpg',1),(6,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/292/524401c7afd06.jpg',1),(7,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/285/51a5864f84949.jpg',2),(8,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/285/51a58657264ec.jpg',2),(9,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/285/51a5864c7b8cc.jpg',2),(10,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/285/51a58652908cc.jpg',2),(11,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/285/51a5865ba5ff4.jpg',2),(12,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/285/51a586548ecaa.jpg',2),(13,'2016-03-30 11:16:14','','http://p2.ol-img.com/product/raw/3/285/51a5865923a98.jpg',2),(14,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/1/87/51a5893229fdc.jpg',3),(15,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/1/87/51a589350bf2a.jpg',3),(16,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/1/87/51a58936b59c8.jpg',3),(17,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/1/87/51a5893b1d069.jpg',3),(18,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/1/87/51a5892f399b3.jpg',3),(19,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/1/87/51a589393dfad.jpg',3),(20,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/269/51a589a6d92d0.jpg',4),(21,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/269/51a589a3af33e.jpg',4),(22,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/269/51a589a0758c6.jpg',4),(23,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/269/51a5899d8728d.jpg',4),(24,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/269/51a589aaaa889.jpg',4),(25,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/269/51a589ad748ea.jpg',4),(26,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a1e1ea62e.jpg',5),(27,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a1d6481b0.jpg',5),(28,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51ac63fb096c6.jpg',5),(29,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a1d952147.jpg',5),(30,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a1dc6a077.jpg',5),(31,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a1dedd78c.jpg',5),(32,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a1020c27b.jpg',6),(33,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a10ad492b.jpg',6),(34,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a10027083.jpg',6),(35,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a104d3a8f.jpg',6),(36,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a10dbb300.jpg',6),(37,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51ac62d3a038c.jpg',6),(38,'2016-03-30 11:16:15','','http://p2.ol-img.com/product/raw/3/281/51a5a107d98a5.jpg',6);
/*!40000 ALTER TABLE `t_product_thumbnail_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product_title_image`
--

DROP TABLE IF EXISTS `t_product_title_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_title_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product_title_image`
--

LOCK TABLES `t_product_title_image` WRITE;
/*!40000 ALTER TABLE `t_product_title_image` DISABLE KEYS */;
INSERT INTO `t_product_title_image` VALUES (1,NULL,'','http://p2.ol-img.com/product/100x100/3/292/524401b4410c4.jpg'),(2,NULL,'','http://p2.ol-img.com/product/100x100/3/285/51a58641def8d.jpg'),(3,NULL,'','http://p2.ol-img.com/product/100x100/1/87/51a5891fd140d.jpg'),(4,NULL,'','http://p2.ol-img.com/product/100x100/3/269/51a5898ca5b94.jpg'),(5,NULL,'','http://p2.ol-img.com/product/100x100/3/281/51a5a1c0eb3e5.jpg'),(6,NULL,'','http://p2.ol-img.com/product/100x100/3/281/51a5a0f4a7434.jpg');
/*!40000 ALTER TABLE `t_product_title_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_signin`
--

DROP TABLE IF EXISTS `t_signin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_signin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_21aw8dsyyj7edb8vrv3fmtg4j` (`email`),
  UNIQUE KEY `UK_mok77p3bndo9fu1ncmh7w3sfq` (`mobile`),
  UNIQUE KEY `UK_2crb8f37fxl94axfjogfbndjx` (`user_name`),
  KEY `FK_51er2b9pdsvs6ubqfinxv3ja4` (`user_id`),
  CONSTRAINT `FK_51er2b9pdsvs6ubqfinxv3ja4` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_signin`
--

LOCK TABLES `t_signin` WRITE;
/*!40000 ALTER TABLE `t_signin` DISABLE KEYS */;
INSERT INTO `t_signin` VALUES (1,'2016-03-30 11:16:14','jc0918@163.com','18001691011','123456','2016-03-30 11:16:14','jc0918',1),(2,'2016-03-30 11:16:14','joeyliu616@live.cn','18566231281','123456','2016-03-30 11:16:14','joeyliu616',2),(3,'2016-03-30 11:16:14','gph203@163.com','18666665875','123456','2016-03-30 11:16:14','gph203',3);
/*!40000 ALTER TABLE `t_signin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_group_id` int(11) DEFAULT NULL,
  `user_profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_khffl6reejpux8t1hu44g054m` (`user_group_id`),
  KEY `FK_rkqu5q1h5378be9607ka8f8nf` (`user_profile_id`),
  CONSTRAINT `FK_khffl6reejpux8t1hu44g054m` FOREIGN KEY (`user_group_id`) REFERENCES `t_user_group` (`id`),
  CONSTRAINT `FK_rkqu5q1h5378be9607ka8f8nf` FOREIGN KEY (`user_profile_id`) REFERENCES `t_user_profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,1,1),(2,2,2),(3,3,3);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_group`
--

DROP TABLE IF EXISTS `t_user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pblj897th68bw5ne67x1ofn4t` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_group`
--

LOCK TABLES `t_user_group` WRITE;
/*!40000 ALTER TABLE `t_user_group` DISABLE KEYS */;
INSERT INTO `t_user_group` VALUES (1,'2016-03-30 11:16:14','general-anent','2016-03-30 11:16:14'),(2,'2016-03-30 11:16:14','distributor','2016-03-30 11:16:14'),(3,'2016-03-30 11:16:14','retailer','2016-03-30 11:16:14');
/*!40000 ALTER TABLE `t_user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_group_user_roles`
--

DROP TABLE IF EXISTS `t_user_group_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_group_user_roles` (
  `t_user_group_id` int(11) NOT NULL,
  `user_roles_id` int(11) NOT NULL,
  PRIMARY KEY (`t_user_group_id`,`user_roles_id`),
  KEY `FK_7w1h2na9mou72vexylsac8ruv` (`user_roles_id`),
  CONSTRAINT `FK_1yidxldwhr3083x0n60aq8tlw` FOREIGN KEY (`t_user_group_id`) REFERENCES `t_user_group` (`id`),
  CONSTRAINT `FK_7w1h2na9mou72vexylsac8ruv` FOREIGN KEY (`user_roles_id`) REFERENCES `t_user_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_group_user_roles`
--

LOCK TABLES `t_user_group_user_roles` WRITE;
/*!40000 ALTER TABLE `t_user_group_user_roles` DISABLE KEYS */;
INSERT INTO `t_user_group_user_roles` VALUES (1,1),(2,1),(2,2),(3,2);
/*!40000 ALTER TABLE `t_user_group_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_profile`
--

DROP TABLE IF EXISTS `t_user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `wechat_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_profile`
--

LOCK TABLES `t_user_profile` WRITE;
/*!40000 ALTER TABLE `t_user_profile` DISABLE KEYS */;
INSERT INTO `t_user_profile` VALUES (1,'上海杨浦区四平路1239号同济大学内(近彰武路)','2016-03-30 11:16:14','jc0918@163.com','18001691011',NULL,'彭江城','2016-03-30 11:16:14',NULL),(2,'新疆昌吉北京南路','2016-03-30 11:16:14','joeyliu616@live.cn','18566231281',NULL,'刘朝','2016-03-30 11:16:14',NULL),(3,'湖北麻城黄金桥','2016-03-30 11:16:14','gph203@163.com','18666665875',NULL,'高成','2016-03-30 11:16:14',NULL);
/*!40000 ALTER TABLE `t_user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9595rls2euk01ce54xgpg7ovc` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,'2016-03-30 11:16:14','sell','2016-03-30 11:16:14'),(2,'2016-03-30 11:16:14','buy','2016-03-30 11:16:14');
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-30 11:26:36