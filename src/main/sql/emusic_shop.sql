CREATE DATABASE  IF NOT EXISTS `emusic_shop`;
USE `emusic_shop`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) DEFAULT NULL,
  `product_category` varchar(45) DEFAULT NULL,
  `product_description` varchar(45) DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  `product_condition` varchar(45) DEFAULT NULL,
  `product_status` varchar(45) DEFAULT NULL,
  `unit_in_stock` int(11) DEFAULT NULL,
  `product_manufacturer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `product` VALUES 
	(1,'Violin','Feder','i dont no', 1000,'condition','Active', 7,'manufacturer'),
    (2,'Piano','Feder','i dont no', 5000,'condition','Active', 7,'manufacturer'),
	(3,'Gitar','Feder','i dont no', 1000,'condition','Active', 7,'manufacturer');
    
      
      
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

