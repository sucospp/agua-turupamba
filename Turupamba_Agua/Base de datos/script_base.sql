CREATE DATABASE  IF NOT EXISTS `turupamba_agua` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `turupamba_agua`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: turupamba_agua
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracion` (
  `idConfiguracion` int(11) NOT NULL AUTO_INCREMENT,
  `Descripción` varchar(45) DEFAULT NULL,
  `Valor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idConfiguracion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (1,'Codigo_Activacion','12081992'),(2,'Activado','1');
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_detalle_lecturas`
--

DROP TABLE IF EXISTS `factura_detalle_lecturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura_detalle_lecturas` (
  `Facturas_idFacturas` int(11) NOT NULL,
  `Lecturas_idLecturas` int(11) NOT NULL,
  PRIMARY KEY (`Facturas_idFacturas`,`Lecturas_idLecturas`),
  KEY `fk_Factura_detalle_lecturas_Lecturas1_idx` (`Lecturas_idLecturas`),
  CONSTRAINT `fk_Factura_detalle_lecturas_Facturas1` FOREIGN KEY (`Facturas_idFacturas`) REFERENCES `facturas` (`idFacturas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Factura_detalle_lecturas_Lecturas1` FOREIGN KEY (`Lecturas_idLecturas`) REFERENCES `lecturas` (`idLecturas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_detalle_lecturas`
--

LOCK TABLES `factura_detalle_lecturas` WRITE;
/*!40000 ALTER TABLE `factura_detalle_lecturas` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_detalle_lecturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura_detalle_multas`
--

DROP TABLE IF EXISTS `factura_detalle_multas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura_detalle_multas` (
  `Facturas_idFacturas` int(11) NOT NULL,
  `Multas_idMultas` int(11) NOT NULL,
  PRIMARY KEY (`Facturas_idFacturas`,`Multas_idMultas`),
  KEY `fk_Factura_detalle_Multas1_idx` (`Multas_idMultas`),
  CONSTRAINT `fk_Factura_detalle_Facturas1` FOREIGN KEY (`Facturas_idFacturas`) REFERENCES `facturas` (`idFacturas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Factura_detalle_Multas1` FOREIGN KEY (`Multas_idMultas`) REFERENCES `multas` (`idMultas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_detalle_multas`
--

LOCK TABLES `factura_detalle_multas` WRITE;
/*!40000 ALTER TABLE `factura_detalle_multas` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura_detalle_multas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facturas` (
  `idFacturas` int(11) NOT NULL AUTO_INCREMENT,
  `Total` decimal(10,0) DEFAULT NULL,
  `Fecha_Factura` datetime DEFAULT NULL,
  `Personas_idPersonas` int(11) NOT NULL,
  `Medidor_idMedidor` int(11) NOT NULL,
  `Mes_recibo` date DEFAULT NULL,
  PRIMARY KEY (`idFacturas`,`Personas_idPersonas`,`Medidor_idMedidor`),
  KEY `fk_Facturas_Personas1_idx` (`Personas_idPersonas`),
  KEY `fk_Facturas_Medidor1_idx` (`Medidor_idMedidor`),
  CONSTRAINT `fk_Facturas_Medidor1` FOREIGN KEY (`Medidor_idMedidor`) REFERENCES `medidor` (`idMedidor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Facturas_Personas1` FOREIGN KEY (`Personas_idPersonas`) REFERENCES `personas` (`idPersonas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturas`
--

DROP TABLE IF EXISTS `lecturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecturas` (
  `idLecturas` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha_lectura` date DEFAULT NULL,
  `Lectura` varchar(45) DEFAULT NULL,
  `Personas_idPersonas` int(11) NOT NULL,
  PRIMARY KEY (`idLecturas`),
  KEY `fk_Lecturas_Personas1_idx` (`Personas_idPersonas`),
  CONSTRAINT `fk_Lecturas_Personas1` FOREIGN KEY (`Personas_idPersonas`) REFERENCES `personas` (`idPersonas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturas`
--

LOCK TABLES `lecturas` WRITE;
/*!40000 ALTER TABLE `lecturas` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medidor`
--

DROP TABLE IF EXISTS `medidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medidor` (
  `idMedidor` int(11) NOT NULL AUTO_INCREMENT,
  `numero_medidor` varchar(10) DEFAULT NULL,
  `Personas_idPersonas` int(11) NOT NULL,
  PRIMARY KEY (`idMedidor`,`Personas_idPersonas`),
  KEY `fk_Medidor_Personas1_idx` (`Personas_idPersonas`),
  CONSTRAINT `fk_Medidor_Personas1` FOREIGN KEY (`Personas_idPersonas`) REFERENCES `personas` (`idPersonas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medidor`
--

LOCK TABLES `medidor` WRITE;
/*!40000 ALTER TABLE `medidor` DISABLE KEYS */;
INSERT INTO `medidor` VALUES (1,'922703',1),(3,'54687',4),(4,'123456789',6),(5,'4564',4),(6,'4654',10);
/*!40000 ALTER TABLE `medidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multas`
--

DROP TABLE IF EXISTS `multas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multas` (
  `idMultas` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion_multa` varchar(45) DEFAULT NULL,
  `Costo` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`idMultas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multas`
--

LOCK TABLES `multas` WRITE;
/*!40000 ALTER TABLE `multas` DISABLE KEYS */;
/*!40000 ALTER TABLE `multas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `idPersonas` int(11) NOT NULL AUTO_INCREMENT,
  `Nombres` varchar(100) DEFAULT NULL,
  `Cedula` varchar(15) DEFAULT NULL,
  `Direccion` varchar(45) DEFAULT NULL,
  `Email` varchar(25) DEFAULT NULL,
  `Activo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idPersonas`),
  UNIQUE KEY `Cedula_UNIQUE` (`Cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'PIEDRA ARGUDO EULALIA','0105777932','lala','lolo',1),(3,'ALVARADO PIEDRA DIANA MARGARITA','0132165646','ASDSAD','ASDASDASD',1),(4,'ARGUDO JOSEFINA','011245789','lololo','lololo',1),(5,'ALVARADO FIDEL',NULL,NULL,NULL,1),(6,'VAZCONES MARGARITA','54321','','',1),(7,'ALVARADO VAZCONES MELANIA DOLORES','2222','','',1),(8,'ALVARADO VAZCONES ESTELA',NULL,NULL,NULL,1),(9,'ALVARADO VAZCONES JUAN ARCIDES','55555','','',1),(10,'ALVARADO ARGUDO HUMBERTO','1234567','','',1),(11,'ARGUDO ARGUDO AMPARITO','77777','','',1),(12,'ARGUDO ARGUDO ARCESIO EULOGIO',NULL,'popopo','',1),(13,'ARGUDO ARGUDO BENIGNO',NULL,NULL,NULL,1),(14,'PIEDRA LIGIA','123','','popopoo',1),(15,'ARGUDO ARGUDO GLORIA','7777444','','',1),(16,'ARGUDO ARGUDO HIPOLITO MACARIO',NULL,NULL,NULL,1),(17,'ARGUDO ARGUDO HILDA',NULL,NULL,NULL,1),(18,'ARGUDO ARGUDO JORGE RICARDO ',NULL,NULL,NULL,1),(19,'ARGUDO ARGUDO MANUEL ORLANDO',NULL,NULL,NULL,1),(20,'ARGUDO ARGUDO MAURO IVAN ',NULL,NULL,NULL,1),(21,'ARGUDO ARGUDO NUBE BARBARITA',NULL,NULL,NULL,1),(22,'ARGUDO CARDENAS FAUSTO JOAQUIN',NULL,NULL,NULL,1),(23,'ARGUDO CLEOTILDE',NULL,NULL,NULL,1),(24,'ARGUDO CORONEL FLORENCIO BARTOLO',NULL,NULL,NULL,1),(25,'ARGUDO GOMESCUELLO LUIS CLAUDIO',NULL,NULL,NULL,1),(26,'ARGUDO GUTIERREZ PIEDAD MARGARITA',NULL,NULL,NULL,1),(27,'ARGUDO MORA CELSO',NULL,NULL,NULL,1),(28,'ARGUDO VAZCONES LASTENIA MARGARITA',NULL,NULL,NULL,1),(29,'ARGUDO VAZCONES TARGELIA INES',NULL,NULL,NULL,1),(30,'ARGUDO VAZCONES FLORO HERNAN',NULL,NULL,NULL,1),(31,'ARGUDO VAZCONEZ HORTENCIA ABIGAIL',NULL,NULL,NULL,1),(32,'ARGUDO VAZCONEZ LEONILA ROMELIA',NULL,NULL,NULL,1),(33,'ARGUDO WILLIAN',NULL,NULL,NULL,1),(34,'ASITIMBAY LUIS',NULL,NULL,NULL,1),(35,'CORONEL ALCIRA',NULL,NULL,NULL,1),(36,'CORONEL ANA ELVIRA',NULL,NULL,NULL,1),(37,'CORONEL CARDENAS ANTONIO',NULL,NULL,NULL,1),(38,'CORONEL ELIANA',NULL,NULL,NULL,1),(39,'CORONEL GALO',NULL,NULL,NULL,1),(40,'ARGUDO CORONEL CARMELINA',NULL,NULL,NULL,1),(41,'CORONEL TORRES RIGOBERTO',NULL,NULL,NULL,1),(42,'CORONEL TORRES MEDARDO',NULL,NULL,NULL,1),(43,'DUTAN CUENCA SANTIAGO',NULL,NULL,NULL,1),(44,'DUTAN GUALLPA MARIA REINA',NULL,NULL,NULL,1),(45,'ZUMBA MANUEL',NULL,NULL,NULL,1),(46,'DUTAN LLIGUICHUSCA NARCIZA DE JESUS ',NULL,NULL,NULL,1),(47,'DUTAN MIZQUIRI  AURORA',NULL,NULL,NULL,1),(48,'FALCONI ARGUDO MARIA EUFEMIA',NULL,NULL,NULL,1),(49,'FALCONI ARGUDO NICOLAS JAVIER',NULL,NULL,NULL,1),(50,'GAÑAY INES',NULL,NULL,NULL,1),(51,'GUAMAN LEMA LUIS',NULL,NULL,NULL,1),(52,'GUTIERREZ CORONEL ROLANDO',NULL,NULL,NULL,1),(53,'GUTIEREZ ARGUDO DOLORES',NULL,NULL,NULL,1),(54,'LAZO TORRES LUIS',NULL,NULL,NULL,1),(55,'LAZO PANORA LUZ AMERICA',NULL,NULL,NULL,1),(56,'MINCHALA PAUCAR MERCEDES',NULL,NULL,NULL,1),(57,'MIZHQUIRI GLADIS',NULL,NULL,NULL,1),(58,'MIZHQUIRI INES',NULL,NULL,NULL,1),(59,'MIZHQUIRI ZOILA',NULL,NULL,NULL,1),(60,'MIZHQUIRI MARTHA',NULL,NULL,NULL,1),(61,'MIZHQUIRI PALAGUACHI TRANSITO',NULL,NULL,NULL,1),(62,'MORA ARGUDO RODOLFO',NULL,NULL,NULL,1),(63,'MORA ARGUDO NELVA',NULL,NULL,NULL,1),(64,'MARLENE ARGUDO',NULL,NULL,NULL,1),(65,'MORA ARGUDO TRANSITO',NULL,NULL,NULL,1),(66,'PALAGUACHI MARTHA  CECILIA',NULL,NULL,NULL,1),(67,'PAZTUIZACA VIJAY  MARIA ANGELITA',NULL,NULL,NULL,1),(68,'PEZANTES GOMESCUELLO SANDRA',NULL,NULL,NULL,1),(69,'PIEDRA ARGUDO ABDON ARTURO',NULL,NULL,NULL,1),(70,'PIEDRA AVILA MARIA DOLORES',NULL,NULL,NULL,1),(71,'PIEDRA WILMER',NULL,NULL,NULL,1),(72,'VAZCONES ERMILA',NULL,NULL,NULL,1),(73,'PIEDRA ARGUDO MARTHA AMPARO',NULL,NULL,NULL,1),(74,'PIEDRA ARGUDO ROSA GUADALUPE',NULL,NULL,NULL,1),(75,'PIEDRA TORRES SANDRA JAKELINE',NULL,NULL,NULL,1),(76,'ZUMBA DÚTAN JÓSE ORLANDO',NULL,NULL,NULL,1),(77,'ZUMBA MARIA FLOR',NULL,NULL,NULL,1),(78,'TACURI DÚTAN RAÚL ANTONIO',NULL,NULL,NULL,1),(79,'TELLO ARGUDO JÓSE VICENTE',NULL,NULL,NULL,1),(80,'TELLO ARGUDO INES',NULL,NULL,NULL,1),(81,'TORRES LAURA',NULL,NULL,NULL,1),(82,'USHCA MIZHQUIRI  ASUNCION',NULL,NULL,NULL,1),(83,'ALVARADO MERCY',NULL,NULL,NULL,1),(84,'VAZCONES CARDENAS CESAR ALVINO',NULL,NULL,NULL,1),(85,'VAZCONES GOMESCUELLO MARIA LUISA',NULL,NULL,NULL,1),(86,'VAZCONES GOMESCUELLO MARIA CARMELA',NULL,NULL,NULL,1),(87,'VAZCONES RAMIRO',NULL,NULL,NULL,1),(88,'VAZCONES ARGUDO JOSEFINA MARGARITA',NULL,NULL,NULL,1),(89,'VAZCONES CARDENAS CESAR RAFAEL ',NULL,NULL,NULL,1),(90,'VAZCONES  CORONEL ARCENIO RAÚL',NULL,NULL,NULL,1),(91,'CORONEL CONCEPCION ELSA',NULL,NULL,NULL,1),(92,'ESCUELA EUJENIO ESPEJO ',NULL,NULL,NULL,1),(93,'ALEJANDRINA MIZQUIRI',NULL,NULL,NULL,1),(94,'LIVIA VÁSCONEZ',NULL,NULL,NULL,1),(95,'OFICINAS PUBLICAS',NULL,NULL,NULL,1),(96,'CANCHAS DEPORTIVAS ',NULL,NULL,NULL,1),(97,'TORRES ARGUDO JUAN DIEGO','0105777845','asdsadasd','asdasdasd',1);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono`
--

DROP TABLE IF EXISTS `telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefono` (
  `idTelefono` int(11) NOT NULL AUTO_INCREMENT,
  `Telefono` varchar(20) DEFAULT NULL,
  `Personas_idPersonas` int(11) NOT NULL,
  PRIMARY KEY (`idTelefono`,`Personas_idPersonas`),
  KEY `fk_Telefono_Personas_idx` (`Personas_idPersonas`),
  CONSTRAINT `fk_Telefono_Personas` FOREIGN KEY (`Personas_idPersonas`) REFERENCES `personas` (`idPersonas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono`
--

LOCK TABLES `telefono` WRITE;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
INSERT INTO `telefono` VALUES (1,'28448923',1),(5,'132546',10),(6,'123456879',6),(8,'654321',10),(9,'0983881952',1);
/*!40000 ALTER TABLE `telefono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `idUsuarios` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre_Usuario` varchar(20) DEFAULT NULL,
  `Contraseña` varchar(20) DEFAULT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Rol` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idUsuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'sucospp','sucospp1208','Juan Diego Torres',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-16 21:15:48
