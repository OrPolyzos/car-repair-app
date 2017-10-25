LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2','Oulof Palme','25439','179329844','Glykeria@Katsari.com','Glykeria','Katsari','JEGJBewHfRsoL','Admin'),(2,'4','Oulof Palme','15772','\n916320170','Glykeria@Argyroiliopoulos.com','Glykeria','Argyroiliopoulos','pebwZGWVqKAJPA','User'),(3,'4','Oulof Palme','25439','\n286282568','Glykeria@Peristeris.com','Glykeria','Peristeris','eJTnrQ','User'),(4,'6','Oulof Palme','15772','\n755600223','Glykeria@Vlahopoulos.com','Glykeria','Vlahopoulos','ZgpSdPWrjvJ','User'),(5,'6','Oulof Palme','93489','\n869241875','Spyros@Katsari.com','Spyros','Katsari','cEnixfHk','User'),(6,'9','Oulof Palme','93489','\n965101855','Spyros@Argyroiliopoulos.com','Spyros','Argyroiliopoulos','wPxjdJKCnvCnHDQ','User'),(7,'1','Papagou','93489','\n563628245','Spyros@Vlahopoulos.com','Spyros','Vlahopoulos','tUAbhcerohzEi','User'),(8,'2','Papagou','48859','\n993335045','Spyros@Kormaris.com','Spyros','Kormaris','yzkURaeNF','User'),(9,'6','Papagou','93489','\n855058789','Spyros@Kotsidimos.com','Spyros','Kotsidimos','YgifbpQNo','User'),(10,'9','Papagou','15772','\n627977091','Christos@Katsari.com','Christos','Katsari','AxSHozIIyqHluW','User'),(11,'11','Papagou','25439','\n408693497','Christos@Peristeris.com','Christos','Peristeris','WGPcOPsv','User'),(12,'7','Xalandriou','18975','\n170657254','Christos@Vlahopoulos.com','Christos','Vlahopoulos','FsIQhRqhbxk','User'),(13,'7','Xalandriou','48859','\n919743860','Christos@Kotsidimos.com','Christos','Kotsidimos','VhgHgbbR','User'),(14,'8','Xalandriou','18975','\n758641051','Christos@Psarrakos.com','Christos','Psarrakos','fQffJnvvFoyMbnC','User'),(15,'10','Xalandriou','23885','\n397169204','Christos@Melakis.com','Christos','Melakis','TSBqTxavygjxs','User'),(16,'2','Syggrou','18975','\n585040804','John@Katsari.com','John','Katsari','iCCNeqBgms','User'),(17,'4','Syggrou','23885','\n471768175','John@Argyroiliopoulos.com','John','Argyroiliopoulos','wHrOvQsZenORnOe','User'),(18,'8','Syggrou','15772','\n009567587','John@Vlahopoulos.com','John','Vlahopoulos','ZYgFtGQCUl','User'),(19,'10','Syggrou','93489','\n732396910','John@Melakis.com','John','Melakis','nAxPgChldwl','User'),(20,'1','Athinwn','15771','\n768470722','John@Matthaios.com','John','Matthaios','SHMXNxNcdkSR','User'),(21,'1','Athinwn','48859','\n600401764','Nasos@Katsari.com','Nasos','Katsari','KbyBzpSnOffRL','User'),(22,'2','Athinwn','15772','\n639142060','Nasos@Peristeris.com','Nasos','Peristeris','pXUOXDvRZw','User'),(23,'2','Athinwn','23885','\n027507428','Nasos@Kormaris.com','Nasos','Kormaris','MfGYNZk','User'),(24,'5','Athinwn','23885','\n679160308','Nasos@Kotsidimos.com','Nasos','Kotsidimos','vgpOzGwnajhK','User'),(25,'6','Athinwn','25439','\n216683654','Nasos@Melakis.com','Nasos','Melakis','UcFKrjOstzgP','User'),(26,'6','Athinwn','18975','\n143919732','Giannis@Katsari.com','Giannis','Katsari','NmoiUS','User'),(27,'8','Athinwn','18975','\n286197374','Giannis@Argyroiliopoulos.com','Giannis','Argyroiliopoulos','AjIlPyAvUZ','User'),(28,'8','Athinwn','23885','\n477555521','Giannis@Vlahopoulos.com','Giannis','Vlahopoulos','MXzxLyTEt','User'),(29,'1','Spartis','23885','\n514608720','Giannis@Psarrakos.com','Giannis','Psarrakos','UPMsHtcpIxxHC','User'),(30,'5','Spartis','15772','\n683732444','Giannis@Pagonis.com','Giannis','Pagonis','DZJQUchrsOg','User'),(31,'1','Somewhere','11111','\n111111111','admin@admin.com','Admin','Admin','admin123','Admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `parts` WRITE;
/*!40000 ALTER TABLE `parts` DISABLE KEYS */;
INSERT INTO `parts` VALUES (1,'Gear shift',120),(2,'Windshield',90),(3,'Machine Nissan',5600),(4,'Radiator',70),(5,'Tyre',30),(6,'Car door',80),(7,'Car Battery',140),(8,'Windscreen Wipers',56),(9,'Car Lights',84),(10,'Steering wheel',92);
/*!40000 ALTER TABLE `parts` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES ('ABC-1234','Volkswagen','Blue','Diesel','Polo',22,'2014'),('ADL-9988','Nissan','Red','Petrol','200SX',22,'1994'),('ADS-3444','Citroen','Blue','Petrol','C4',22,'2010'),('ASD-1333','Honda','Black','Petrol','S2000',7,'2004'),('ASD-2322','Skoda','Silver','Diesel','Octavia',3,'2008'),('ASD-9899','Seat','Yellow','Petrol','Leon',4,'2010'),('JHK-9890','Chevrolet','Yellow','Petrol','Camaro',1,'2012'),('LAM-9899','BMW','Black','Petrol','M3 E30',6,'1995'),('LKJ-8988','Toyota','White','Petrol','Starlet',2,'1995'),('LKJ-9874','Volkswagen','Red','Petrol','Golf Gti',3,'2012'),('LKO-9098','Subaru','Blue','Petrol','Impreza WRX STI',6,'2002'),('LLL-1200','TVR','Green','Petrol','Tuscan',8,'2002'),('MGK-9898','Nissan','Silver','Diesel','Navara',4,'2006'),('MKG-1300','Dodge','Black','Petrol','Viper',7,'2012'),('MLK-8988','BMW','White','Diesel','Z4',4,'2006'),('NJK-1233','Mercedes-Benz','Black','Diesel','A-Class',5,'2008'),('PLF-0999','Ford','BLue','Diesel','Fiesta',1,'2009'),('POI-9989','Porsche','Orange','Petrol','911 GT2 RS',7,'2004'),('POM-2900','Renault','Yellow','Petrol','Megane RS',2,'2009'),('VFS-9898','Renault','Blue','Petrol','Clio Williams',6,'1994'),('VGA-1294','Audi','White','Petrol','S3',3,'2008'),('VGL-9829','Volkswagen','Blue','Diesel','Touareg',1,'2012'),('VGM-9239','Ford','Silver','Petrol','Mustang',23,'2014');
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `repair_types` WRITE;
/*!40000 ALTER TABLE `repair_types` DISABLE KEYS */;
INSERT INTO `repair_types` VALUES (1,150,'Small Service'),(2,300,'Great Service');
/*!40000 ALTER TABLE `repair_types` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `repairs` WRITE;
/*!40000 ALTER TABLE `repairs` DISABLE KEYS */;
INSERT INTO `repairs` VALUES (2,'2017-11-01 01:00:00','Pending','Allagh ladiwn',15,1,'ABC-1234'),(3,'2017-11-07 01:00:00','Pending','Some Tasks',55,1,'ABC-1234'),(4,'2017-11-02 01:00:00','Pending','Tasks',55,1,'ADL-9988');
/*!40000 ALTER TABLE `repairs` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `repair_parts` WRITE;
/*!40000 ALTER TABLE `repair_parts` DISABLE KEYS */;
/*!40000 ALTER TABLE `repair_parts` ENABLE KEYS */;
UNLOCK TABLES;