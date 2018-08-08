-- Data for table `userprofile`

LOCK TABLES `userprofile` WRITE;
/*!40000 ALTER TABLE `userprofile` DISABLE KEYS */;
INSERT INTO `userprofile` VALUES (1,'2018-01-05 14:50:15','admin','Y','N','userFirstName','userGroup','userLastName','pass','mr','admin'),(2,'2018-01-05 14:50:15','user','Y','N','userFirstName','userGroup','userLastName','pass','mr','user');
/*!40000 ALTER TABLE `userprofile` ENABLE KEYS */;
UNLOCK TABLES;

-- Data for table `user_role`

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,NULL,NULL,0,1),(2,NULL,NULL,1,1),(3,NULL,NULL,2,1),(4,NULL,NULL,1,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

