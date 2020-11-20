CREATE DATABASE  IF NOT EXISTS `turupamba_agua` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `turupamba_agua`;
-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: turupamba_agua
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

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
-- Table structure for table `Lista`
--

DROP TABLE IF EXISTS `Lista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Lista` (
  `idLista` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_asistencia` date NOT NULL,
  `Asiste` tinyint(1) DEFAULT NULL,
  `Personas_idPersonas` int(1) NOT NULL,
  PRIMARY KEY (`idLista`,`Personas_idPersonas`),
  KEY `fk_Lista_Personas1_idx` (`Personas_idPersonas`)
) ENGINE=InnoDB AUTO_INCREMENT=223 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lista`
--

LOCK TABLES `Lista` WRITE;
/*!40000 ALTER TABLE `Lista` DISABLE KEYS */;
INSERT INTO `Lista` VALUES (188,'2018-12-18',0,1),(206,'2019-01-21',0,3),(207,'2019-01-21',1,4),(208,'2019-01-21',0,5),(209,'2019-01-21',0,6),(210,'2019-01-21',0,7),(211,'2019-01-21',0,8),(212,'2019-01-21',0,9),(213,'2019-01-21',0,10),(214,'2019-01-21',0,11),(215,'2019-01-21',1,14),(216,'2019-01-21',1,15),(217,'2019-01-21',0,97),(218,'2019-01-21',0,98),(219,'2019-01-21',0,99),(220,'2019-01-21',0,100),(221,'2019-01-21',0,102),(222,'2019-01-21',0,103);
/*!40000 ALTER TABLE `Lista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lista_MInga`
--

DROP TABLE IF EXISTS `Lista_MInga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Lista_MInga` (
  `idLista_MInga` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_minga` date NOT NULL,
  `Asiste` tinyint(1) DEFAULT NULL,
  `Personas_idPersonas` int(11) NOT NULL,
  PRIMARY KEY (`idLista_MInga`,`Personas_idPersonas`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lista_MInga`
--

LOCK TABLES `Lista_MInga` WRITE;
/*!40000 ALTER TABLE `Lista_MInga` DISABLE KEYS */;
INSERT INTO `Lista_MInga` VALUES (232,'2019-01-18',1,100),(233,'2019-01-18',1,10),(234,'2019-01-18',1,5),(235,'2019-01-18',1,3),(236,'2019-01-18',1,8),(237,'2019-01-18',1,9),(238,'2019-01-18',1,7),(239,'2019-01-18',1,11),(240,'2019-01-18',1,15),(241,'2019-01-18',0,4),(242,'2019-01-18',0,99),(243,'2019-01-18',0,98),(244,'2019-01-18',0,103),(245,'2019-01-18',0,1),(246,'2019-01-18',0,14),(247,'2019-01-18',0,102),(248,'2019-01-18',0,97),(249,'2019-01-18',0,6);
/*!40000 ALTER TABLE `Lista_MInga` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (1,'Codigo_Activacion','12081992'),(2,'Activado','1'),(3,'Cobro_Basico','2');
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
  `Lecturas_idLecturas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_detalle_lecturas`
--

LOCK TABLES `factura_detalle_lecturas` WRITE;
/*!40000 ALTER TABLE `factura_detalle_lecturas` DISABLE KEYS */;
INSERT INTO `factura_detalle_lecturas` VALUES (1,17),(2,24);
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
  `valor` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_detalle_multas`
--

LOCK TABLES `factura_detalle_multas` WRITE;
/*!40000 ALTER TABLE `factura_detalle_multas` DISABLE KEYS */;
INSERT INTO `factura_detalle_multas` VALUES (1,1,1.00),(1,2,0.00),(1,3,25.00),(1,4,50.00),(2,1,0.50),(2,2,0.00),(2,3,25.00);
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
  `Total` decimal(10,2) DEFAULT NULL,
  `Fecha_Factura` date DEFAULT NULL,
  `Personas_idPersonas` int(11) NOT NULL,
  `Medidor_idMedidor` int(11) DEFAULT NULL,
  `Mes_recibo` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`idFacturas`,`Personas_idPersonas`),
  KEY `fk_Facturas_Personas1_idx` (`Personas_idPersonas`),
  CONSTRAINT `fk_Facturas_Personas1` FOREIGN KEY (`Personas_idPersonas`) REFERENCES `personas` (`idPersonas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (1,78.00,'2019-01-22',1,10,'DICIEMBRE'),(2,27.50,'2019-01-22',12,11,'DICIEMBRE');
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturas`
--

LOCK TABLES `lecturas` WRITE;
/*!40000 ALTER TABLE `lecturas` DISABLE KEYS */;
INSERT INTO `lecturas` VALUES (1,'2019-01-17','55',3),(3,'2019-01-17','0',9),(4,'2019-01-17','12345',10),(5,'2019-01-17','0',4),(6,'2019-01-17','214',14),(7,'2019-01-17','70',15),(8,'2019-01-10','4444',7),(9,'2018-12-15','45',4),(10,'2019-01-17','0',5),(11,'2019-01-17','0',5),(12,'2019-01-17','0',6),(13,'2019-01-17','0',5),(14,'2019-01-17','0',5),(15,'2019-01-17','0',1),(16,'2019-01-17','4567',98),(17,'2018-12-24','100',1),(18,'2018-12-24','100',3),(19,'2018-12-24','200',5),(20,'2018-12-24','20',6),(21,'2018-11-20','1000',1),(22,'2018-12-20','400',97),(23,'2019-01-21','50',1),(24,'2018-12-14','15',12);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medidor`
--

LOCK TABLES `medidor` WRITE;
/*!40000 ALTER TABLE `medidor` DISABLE KEYS */;
INSERT INTO `medidor` VALUES (3,'54687',4),(4,'123456789',6),(6,'4654',10),(7,'123213456',9),(8,'16547',97),(10,'12345647',1),(11,'999999',12);
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
  `Costo` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idMultas`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multas`
--

LOCK TABLES `multas` WRITE;
/*!40000 ALTER TABLE `multas` DISABLE KEYS */;
INSERT INTO `multas` VALUES (1,'MULTA POR EXCESO',0.00),(2,'MULTA POR MORA',0.50),(3,'MULTA POR MINGA',25.00),(4,'MULTA POR REINSTALACION',50.00),(5,'MULTA POR ASAMBLEA',15.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'PIEDRA ARGUDO EULALIA','0105777932','turupamba','alo@hotmail.com',1),(3,'ALVARADO PIEDRA DIANA MARGARITA','0132165646','ASDSAD','ASDASDASD',1),(4,'ARGUDO JOSEFINA','011245789','lololo','lololo',1),(5,'ALVARADO FIDEL','4567889','','',1),(6,'VAZCONES MARGARITA','54321','','',1),(7,'ALVARADO VAZCONES MELANIA DOLORES','2222','','',1),(8,'ALVARADO VAZCONES ESTELA','01057777777777','','',1),(9,'ALVARADO VAZCONES JUAN ARCIDES','6666','','',1),(10,'ALVARADO ARGUDO HUMBERTO','1234567','','',1),(11,'ARGUDO ARGUDO AMPARITO','77777','','',1),(12,'ARGUDO ARGUDO ARCESIO EULOGIO','12','popopo','',1),(13,'ARGUDO ARGUDO BENIGNO','13',NULL,NULL,1),(14,'PIEDRA LIGIA','123','','popopoo',1),(15,'ARGUDO ARGUDO GLORIA','7777444','','',1),(16,'ARGUDO ARGUDO HIPOLITO MACARIO','16',NULL,NULL,1),(17,'ARGUDO ARGUDO HILDA','17',NULL,NULL,1),(18,'ARGUDO ARGUDO JORGE RICARDO ','18',NULL,NULL,1),(19,'ARGUDO ARGUDO MANUEL ORLANDO','19',NULL,NULL,1),(20,'ARGUDO ARGUDO MAURO IVAN ','20',NULL,NULL,1),(21,'ARGUDO ARGUDO NUBE BARBARITA','21',NULL,NULL,1),(22,'ARGUDO CARDENAS FAUSTO JOAQUIN','22',NULL,NULL,1),(23,'ARGUDO CLEOTILDE','23',NULL,NULL,1),(24,'ARGUDO CORONEL FLORENCIO BARTOLO','24',NULL,NULL,1),(25,'ARGUDO GOMESCUELLO LUIS CLAUDIO','25',NULL,NULL,1),(26,'ARGUDO GUTIERREZ PIEDAD MARGARITA','26',NULL,NULL,1),(27,'ARGUDO MORA CELSO','27',NULL,NULL,1),(28,'ARGUDO VAZCONES LASTENIA MARGARITA','28',NULL,NULL,1),(29,'ARGUDO VAZCONES TARGELIA INES','29',NULL,NULL,1),(30,'ARGUDO VAZCONES FLORO HERNAN','30',NULL,NULL,1),(31,'ARGUDO VAZCONEZ HORTENCIA ABIGAIL','31',NULL,NULL,1),(32,'ARGUDO VAZCONEZ LEONILA ROMELIA','32',NULL,NULL,1),(33,'ARGUDO WILLIAN','33',NULL,NULL,1),(34,'ASITIMBAY LUIS','34',NULL,NULL,1),(35,'CORONEL ALCIRA','35',NULL,NULL,1),(36,'CORONEL ANA ELVIRA','36',NULL,NULL,1),(37,'CORONEL CARDENAS ANTONIO','37',NULL,NULL,1),(38,'CORONEL ELIANA','38',NULL,NULL,1),(39,'CORONEL GALO','39',NULL,NULL,1),(40,'ARGUDO CORONEL CARMELINA','40',NULL,NULL,1),(41,'CORONEL TORRES RIGOBERTO','41',NULL,NULL,1),(42,'CORONEL TORRES MEDARDO','42',NULL,NULL,1),(43,'DUTAN CUENCA SANTIAGO','43',NULL,NULL,1),(44,'DUTAN GUALLPA MARIA REINA','44',NULL,NULL,1),(45,'ZUMBA MANUEL','45',NULL,NULL,1),(46,'DUTAN LLIGUICHUSCA NARCIZA DE JESUS ','46',NULL,NULL,1),(47,'DUTAN MIZQUIRI  AURORA','47',NULL,NULL,1),(48,'FALCONI ARGUDO MARIA EUFEMIA','48',NULL,NULL,1),(49,'FALCONI ARGUDO NICOLAS JAVIER','49',NULL,NULL,1),(50,'GAÑAY INES','50',NULL,NULL,1),(51,'GUAMAN LEMA LUIS','51',NULL,NULL,1),(52,'GUTIERREZ CORONEL ROLANDO','52',NULL,NULL,1),(53,'GUTIEREZ ARGUDO DOLORES','53',NULL,NULL,1),(54,'LAZO TORRES LUIS','54',NULL,NULL,1),(55,'LAZO PANORA LUZ AMERICA','55',NULL,NULL,1),(56,'MINCHALA PAUCAR MERCEDES','56',NULL,NULL,1),(57,'MIZHQUIRI GLADIS','57',NULL,NULL,1),(58,'MIZHQUIRI INES','58',NULL,NULL,1),(59,'MIZHQUIRI ZOILA','59',NULL,NULL,1),(60,'MIZHQUIRI MARTHA','60',NULL,NULL,1),(61,'MIZHQUIRI PALAGUACHI TRANSITO','61',NULL,NULL,1),(62,'MORA ARGUDO RODOLFO','62',NULL,NULL,1),(63,'MORA ARGUDO NELVA','63',NULL,NULL,1),(64,'MARLENE ARGUDO','64',NULL,NULL,1),(65,'MORA ARGUDO TRANSITO','65',NULL,NULL,1),(66,'PALAGUACHI MARTHA  CECILIA','66',NULL,NULL,1),(67,'PAZTUIZACA VIJAY  MARIA ANGELITA','67',NULL,NULL,1),(68,'PEZANTES GOMESCUELLO SANDRA','68',NULL,NULL,1),(69,'PIEDRA ARGUDO ABDON ARTURO','69',NULL,NULL,1),(70,'PIEDRA AVILA MARIA DOLORES','70',NULL,NULL,1),(71,'PIEDRA WILMER','71',NULL,NULL,1),(72,'VAZCONES ERMILA','72',NULL,NULL,1),(73,'PIEDRA ARGUDO MARTHA AMPARO','73',NULL,NULL,1),(74,'PIEDRA ARGUDO ROSA GUADALUPE','74',NULL,NULL,1),(75,'PIEDRA TORRES SANDRA JAKELINE','75',NULL,NULL,1),(76,'ZUMBA DÚTAN JÓSE ORLANDO','76',NULL,NULL,1),(77,'ZUMBA MARIA FLOR','77',NULL,NULL,1),(78,'TACURI DÚTAN RAÚL ANTONIO','78',NULL,NULL,1),(79,'TELLO ARGUDO JÓSE VICENTE','79',NULL,NULL,1),(80,'TELLO ARGUDO INES','80',NULL,NULL,1),(81,'TORRES LAURA','81',NULL,NULL,1),(82,'USHCA MIZHQUIRI  ASUNCION','82',NULL,NULL,1),(83,'ALVARADO MERCY','83',NULL,NULL,1),(84,'VAZCONES CARDENAS CESAR ALVINO','84',NULL,NULL,1),(85,'VAZCONES GOMESCUELLO MARIA LUISA','85',NULL,NULL,1),(86,'VAZCONES GOMESCUELLO MARIA CARMELA','86',NULL,NULL,1),(87,'VAZCONES RAMIRO','87',NULL,NULL,1),(88,'VAZCONES ARGUDO JOSEFINA MARGARITA','88',NULL,NULL,1),(89,'VAZCONES CARDENAS CESAR RAFAEL ','89',NULL,NULL,1),(90,'VAZCONES  CORONEL ARCENIO RAÚL','90',NULL,NULL,1),(91,'CORONEL CONCEPCION ELSA','91',NULL,NULL,1),(92,'ESCUELA EUJENIO ESPEJO ','92',NULL,NULL,1),(93,'ALEJANDRINA MIZQUIRI','93',NULL,NULL,1),(94,'LIVIA VÁSCONEZ','94',NULL,NULL,1),(95,'OFICINAS PUBLICAS','95',NULL,NULL,1),(96,'CANCHAS DEPORTIVAS ','96',NULL,NULL,1),(97,'TORRES ARGUDO JUAN DIEGO','0105777845','asdsadasd','asdasdasd',1),(98,'JORGER','014154','sadadas','sdadsad',1),(99,'ASD','asd','asd','asd',1),(100,'1361','1234','1564','454',1),(102,'SDA','0105','','',1),(103,'LALALALALA','lalalalalla','lalalalal','lalalala',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono`
--

LOCK TABLES `telefono` WRITE;
/*!40000 ALTER TABLE `telefono` DISABLE KEYS */;
INSERT INTO `telefono` VALUES (1,'28448923',1),(5,'132546',10),(6,'123456879',6),(8,'654321',10),(9,'0983881952',1),(11,'28448236',9);
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

--
-- Dumping events for database 'turupamba_agua'
--

--
-- Dumping routines for database 'turupamba_agua'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-22 16:12:38
