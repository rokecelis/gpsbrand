-- MySQL dump 10.13  Distrib 5.6.23, for Linux (x86_64)
--
-- Host: localhost    Database: gps_brand
-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'La llave del domicilio',
  `street` varchar(300) NOT NULL,
  `inside_number` varchar(10) DEFAULT NULL COMMENT 'Número interior',
  `outside_number` varchar(10) DEFAULT NULL COMMENT 'número exterior',
  `colony` varchar(100) DEFAULT NULL COMMENT 'Colonia',
  `zip_code` varchar(10) NOT NULL,
  `state_id` int(11) DEFAULT NULL COMMENT 'la llave de la entidad',
  PRIMARY KEY (`id`),
  KEY `entidad_federativa_domicilio_fk` (`state_id`),
  CONSTRAINT `entidad_federativa_domicilio_fk` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT 'El pais del domicilio',
  `name` varchar(100) DEFAULT NULL COMMENT 'El pais de origen',
  `active` int(11) DEFAULT NULL COMMENT 'si se encuetra activo o no',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='pais';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `device_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'identificador único del dispositvo',
  `mac_address` varchar(50) DEFAULT NULL COMMENT 'mac address',
  `imei` varchar(100) DEFAULT NULL COMMENT 'imei del dispositivo',
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Catalogo de dispositivos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT 'La llave del género',
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='El sexo de la persona: hombre, mujer';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT 'Nombre de la persona',
  `email` varchar(50) DEFAULT NULL COMMENT 'El correo electrónico de la persona',
  `last_name` varchar(100) DEFAULT NULL COMMENT 'apellido paterno',
  `second_name` varchar(100) DEFAULT NULL COMMENT 'Apallido materno',
  `curp` varchar(100) DEFAULT NULL COMMENT 'La curp de la persona',
  `rfc` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL COMMENT 'La fecha de nacimiento',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha de creacion',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'fecha de modificacion',
  `active` int(11) DEFAULT NULL COMMENT 'Si la persona se encuentra activa o no',
  `address_id` bigint(20) DEFAULT NULL COMMENT 'La llave del domicilio',
  `type_person_id` bigint(20) DEFAULT NULL COMMENT 'Catálogo de tipos de persona',
  `genre_id` int(11) DEFAULT NULL COMMENT 'La llave del género',
  PRIMARY KEY (`id`),
  KEY `genero_persona_fk` (`genre_id`),
  KEY `tipo_persona_persona_fk` (`type_person_id`),
  KEY `domicilio_persona_fk` (`address_id`),
  CONSTRAINT `domicilio_persona_fk` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `genero_persona_fk` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipo_persona_persona_fk` FOREIGN KEY (`type_person_id`) REFERENCES `type_person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Conjunto de presonas';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_device`
--

DROP TABLE IF EXISTS `position_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_device` (
  `even_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'marca de la fecha y hora',
  `device_id` bigint(20) DEFAULT NULL COMMENT 'identificador único del dispositvo',
  `latitude` double DEFAULT NULL COMMENT 'latitud',
  `longitude` double DEFAULT NULL COMMENT 'longitud',
  PRIMARY KEY (`even_time`),
  KEY `device_position_device_fk` (`device_id`),
  CONSTRAINT `device_position_device_fk` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla de posiciones del dispositivo';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_device`
--

LOCK TABLES `position_device` WRITE;
/*!40000 ALTER TABLE `position_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `position_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT 'Catalogo de perfiles',
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'ROLE_ADMIN',1),(2,'ROLE_USER',1);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT 'la llave de la entidad',
  `name` varchar(100) NOT NULL,
  `country_id` int(11) DEFAULT NULL COMMENT 'El pais del domicilio',
  PRIMARY KEY (`id`),
  KEY `pais_entidad_federativa_fk` (`country_id`),
  CONSTRAINT `pais_entidad_federativa_fk` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Entidad federativa';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title`
--

DROP TABLE IF EXISTS `title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `title` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'El catálogo de nombramientos',
  `name` varchar(100) DEFAULT NULL COMMENT 'el nombramiento: ministro, diacono, obrero',
  `active` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Catálogo de títulos: se conforma por los tipos de titulos: nombramientos y puesto:\nNombramiento: Ministro, diacono, obrero,\npuesto: Secretario, tesorero, pastor';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title`
--

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title_person`
--

DROP TABLE IF EXISTS `title_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `title_person` (
  `person_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'El catálogo de nombramientos',
  `title_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'El catálogo de nombramientos',
  PRIMARY KEY (`person_id`,`title_id`),
  KEY `titulo_persona_titulo_fk` (`title_id`),
  CONSTRAINT `persona_persona_titulo_fk` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `titulo_persona_titulo_fk` FOREIGN KEY (`title_id`) REFERENCES `title` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='La relación de la persona y sus títulos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title_person`
--

LOCK TABLES `title_person` WRITE;
/*!40000 ALTER TABLE `title_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `title_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_person`
--

DROP TABLE IF EXISTS `type_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type_person` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'Catálogo de tipos de persona',
  `name` varchar(200) DEFAULT NULL COMMENT 'El nombre de tipo de persona',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_person`
--

LOCK TABLES `type_person` WRITE;
/*!40000 ALTER TABLE `type_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'clave primaria de la tabla usuario\n',
  `name` varchar(100) DEFAULT NULL COMMENT 'el usuario',
  `account_non_expired` int(11) DEFAULT NULL COMMENT 'La cuenta ha expirado',
  `last_access_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'La fecha del último acceso',
  `credential_non_expired` int(11) DEFAULT NULL COMMENT 'o\n0: La credencial ha expirado',
  `failed_attempts_counter` int(11) DEFAULT NULL COMMENT 'El contador de intentos fallidos',
  `account_non_locked` int(11) DEFAULT NULL COMMENT 'oqueada\n2: La cuenta esta bloqueada',
  `active` tinyint(1) DEFAULT NULL COMMENT '1 activo\n0 inactivo',
  `password` varchar(100) DEFAULT NULL COMMENT 'La clave del usuario',
  `last_change_password_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'La fecha del último cambio de clave',
  `person_id` bigint(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'fecha creación',
  PRIMARY KEY (`id`),
  KEY `persona_usuario_fk` (`person_id`),
  CONSTRAINT `persona_usuario_fk` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'gerardo.roque',1,'2015-03-03 13:26:39',1,0,1,1,'d033e22ae348aeb5660fc2140aec35850c4da997','2014-10-15 05:00:00',1,'0000-00-00 00:00:00');
INSERT INTO `user` VALUES (2,'alexander.gamez',1,'2015-03-03 13:26:39',1,0,1,1,'d033e22ae348aeb5660fc2140aec35850c4da997','2014-10-15 05:00:00',1,'0000-00-00 00:00:00');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_device`
--

DROP TABLE IF EXISTS `user_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_device` (
  `device_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'identificador único del dispositvo',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'clave primaria de la tabla usuario\n',
  PRIMARY KEY (`device_id`),
  KEY `usuario_user_device_fk` (`user_id`),
  CONSTRAINT `device_user_device_fk` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usuario_user_device_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='relación de usuario y dispositivos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_device`
--

LOCK TABLES `user_device` WRITE;
/*!40000 ALTER TABLE `user_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `profile_id` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT 'clave primaria de la tabla usuario\n',
  KEY `profile_user_profile_fk` (`profile_id`),
  KEY `user_user_profile_fk` (`user_id`),
  CONSTRAINT `profile_user_profile_fk` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_user_profile_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='relacion de usuarios y perfiles';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-03  7:28:45
