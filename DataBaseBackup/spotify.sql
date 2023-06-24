-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: spotify
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `artist_id` text,
  `name` text,
  `password` text,
  `email_address` text,
  `biography` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES ('9cac3f6c-9638-48e6-af68-993485ec4fdb','jdjdjdj','akakka','wwww','eeee'),('1','2','3','4','6'),('6','djdjdj','sssss','555555','0000'),('7','ooooooo','wwwwwww','777777','-------'),('8','pppp','++++','1111','2222222');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liked`
--

DROP TABLE IF EXISTS `liked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liked` (
  `user_id` text NOT NULL,
  `track_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liked`
--

LOCK TABLES `liked` WRITE;
/*!40000 ALTER TABLE `liked` DISABLE KEYS */;
/*!40000 ALTER TABLE `liked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music` (
  `track_id` text NOT NULL,
  `title` text NOT NULL,
  `album` text,
  `genre` text NOT NULL,
  `duration` time NOT NULL,
  `release_date` date NOT NULL,
  `score` double DEFAULT NULL,
  `file_path` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
INSERT INTO `music` VALUES ('4','5','6','7','02:20:30','1999-02-04',2.2,'baraye-shervin-hajipour');
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music_artists`
--

DROP TABLE IF EXISTS `music_artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music_artists` (
  `track_id` text NOT NULL,
  `artist_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_artists`
--

LOCK TABLES `music_artists` WRITE;
/*!40000 ALTER TABLE `music_artists` DISABLE KEYS */;
INSERT INTO `music_artists` VALUES ('4','6'),('4','7'),('4','8');
/*!40000 ALTER TABLE `music_artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music_downloads`
--

DROP TABLE IF EXISTS `music_downloads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music_downloads` (
  `user_id` text NOT NULL,
  `track_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_downloads`
--

LOCK TABLES `music_downloads` WRITE;
/*!40000 ALTER TABLE `music_downloads` DISABLE KEYS */;
/*!40000 ALTER TABLE `music_downloads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_list`
--

DROP TABLE IF EXISTS `play_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play_list` (
  `playlist_id` text NOT NULL,
  `user_id` text NOT NULL,
  `title` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_list`
--

LOCK TABLES `play_list` WRITE;
/*!40000 ALTER TABLE `play_list` DISABLE KEYS */;
INSERT INTO `play_list` VALUES ('1','6c20aa52-b8ca-440d-9e1f-eaa72dc4beab','3'),('2','6c20aa52-b8ca-440d-9e1f-eaa72dc4beab','5');
/*!40000 ALTER TABLE `play_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_musics`
--

DROP TABLE IF EXISTS `playlist_musics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_musics` (
  `playlist_id` text,
  `track_id` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_musics`
--

LOCK TABLES `playlist_musics` WRITE;
/*!40000 ALTER TABLE `playlist_musics` DISABLE KEYS */;
INSERT INTO `playlist_musics` VALUES ('1','4');
/*!40000 ALTER TABLE `playlist_musics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` text NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `email_address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('918a0df8-4a35-4128-9d2a-00a484b3dabd','ooooo','00000','hhh'),('b5b887e0-5fc3-43d7-8b54-bc43b6aa4839','kekek','jejj','kekek'),('e324e2cb-7385-41fc-9074-61f472608de8','slsll','s;;s;',';ss;'),('054e3938-9710-41bb-9198-cc0e2555adaa','jddj','jdjdj','jjdjd'),('0810aa59-f854-4b60-bef4-ee7a0a4193c4','slls','ksks','ksks'),('986bd345-9c65-490d-b806-1a5670ad23d2','lslslls','lsls',';s;;s'),('db8bd9f9-238b-4abe-a244-9d1fa8dad5d6','KDLD','DLLD','DLLD'),('6c20aa52-b8ca-440d-9e1f-eaa72dc4beab','bardia','akbari','djdjdj'),('8c0be978-7066-4b91-b73f-331311923994','kdkd','ososos','ppapapa'),('7d718b6b-0883-41a2-bb12-d60c714826ce','nariman','1234','g');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_artist_follow`
--

DROP TABLE IF EXISTS `user_artist_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_artist_follow` (
  `user_id` text NOT NULL,
  `artist_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_artist_follow`
--

LOCK TABLES `user_artist_follow` WRITE;
/*!40000 ALTER TABLE `user_artist_follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_artist_follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user_follow`
--

DROP TABLE IF EXISTS `user_user_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_user_follow` (
  `user_id_1` text,
  `user_id_2` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user_follow`
--

LOCK TABLES `user_user_follow` WRITE;
/*!40000 ALTER TABLE `user_user_follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_user_follow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-24 20:18:00
