-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: cems
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `closed_exams`
--

DROP TABLE IF EXISTS `closed_exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `closed_exams` (
  `exam_id` int NOT NULL,
  `code` int DEFAULT NULL,
  `test_time` int DEFAULT NULL,
  `date_start` varchar(50) DEFAULT NULL,
  `date_end` varchar(45) DEFAULT NULL,
  `cheat` int DEFAULT NULL,
  `students_number` int DEFAULT NULL,
  PRIMARY KEY (`exam_id`),
  CONSTRAINT `close_exams_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `closed_exams`
--

LOCK TABLES `closed_exams` WRITE;
/*!40000 ALTER TABLE `closed_exams` DISABLE KEYS */;
INSERT INTO `closed_exams` VALUES (7,1234,1,'18/6/2023 21:27','18/6/2023 21:28',0,1),(8,1234,1,'18/6/2023 21:33','18/6/2023 21:34',0,1);
/*!40000 ALTER TABLE `closed_exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) NOT NULL,
  `subject_id` int NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Calculus 1',1),(2,'Calculus 2',1),(3,'Statistics',1),(4,'Quantum Mechanics',2),(5,'Electromagnetism',2),(6,'Thermodynamics',2),(7,'Organic Chemistry',3),(8,'Physical Chemistry',3),(9,'Analytical Chemistry',3),(10,'Genetics',4),(11,'Ecology',4),(12,'Biochemistry',4),(13,'Ancient History',5),(14,'Modern History',5),(15,'World War II',5),(16,'Grammar',6),(17,'Literature',6),(18,'Composition',6),(19,'Algorithms',7),(20,'Data Structures',7),(21,'Operating Systems',7);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_questions`
--

DROP TABLE IF EXISTS `exam_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_questions` (
  `exam_id` int NOT NULL,
  `question_id` int NOT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`exam_id`,`question_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `exam_questions_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`exam_id`),
  CONSTRAINT `exam_questions_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_questions`
--

LOCK TABLES `exam_questions` WRITE;
/*!40000 ALTER TABLE `exam_questions` DISABLE KEYS */;
INSERT INTO `exam_questions` VALUES (7,3,5),(7,7,10),(8,8,11),(9,5,55),(9,6,5),(10,7,11),(11,5,5),(12,3,5),(13,2,55),(13,12,45),(14,5,45),(14,6,55),(15,3,10),(15,7,45),(15,13,45),(16,2,45),(16,12,55);
/*!40000 ALTER TABLE `exam_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exams` (
  `exam_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) NOT NULL,
  `lecturer_id` int NOT NULL,
  `lecturer_comments` text,
  `student_comments` text,
  `test_time` int DEFAULT NULL,
  PRIMARY KEY (`exam_id`),
  KEY `lecturer_id` (`lecturer_id`),
  CONSTRAINT `exams_ibfk_1` FOREIGN KEY (`lecturer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
INSERT INTO `exams` VALUES (7,'Quantum Mechanics',2,'hello','there',1),(8,'Calculus 1',2,'','',1),(9,'Physical Chemistry',2,'gg','gg',30),(10,'Quantum Mechanics',2,'','',115),(11,'Physical Chemistry',2,'dsadas','dsadsa',7),(12,'Quantum Mechanics',2,'111','11',11),(13,'Calculus 2',2,'weee','weee',100),(14,'Physical Chemistry',2,'hhh','hhhhhh',55),(15,'Quantum Mechanics',2,'lectuer','student',150),(16,'Calculus 2',2,'sss','sss',150);
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grades` (
  `examId` int NOT NULL,
  `studentId` int NOT NULL,
  `courseID` int DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `lecturerID` int DEFAULT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`examId`,`studentId`),
  KEY `studentId` (`studentId`),
  KEY `courseID` (`courseID`),
  KEY `lecturerID` (`lecturerID`),
  CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`examId`) REFERENCES `exams` (`exam_id`),
  CONSTRAINT `grades_ibfk_2` FOREIGN KEY (`studentId`) REFERENCES `users` (`id`),
  CONSTRAINT `grades_ibfk_3` FOREIGN KEY (`courseID`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `grades_ibfk_4` FOREIGN KEY (`lecturerID`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES (7,1,4,0,2,'Quantum Mechanics','pending',NULL),(8,1,1,0,2,'Calculus 1','pending',NULL);
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `open_exams`
--

DROP TABLE IF EXISTS `open_exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `open_exams` (
  `exam_id` int NOT NULL,
  `code` int DEFAULT NULL,
  `test_time` int DEFAULT NULL,
  PRIMARY KEY (`exam_id`),
  CONSTRAINT `open_exams_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `open_exams`
--

LOCK TABLES `open_exams` WRITE;
/*!40000 ALTER TABLE `open_exams` DISABLE KEYS */;
/*!40000 ALTER TABLE `open_exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `question_text` text NOT NULL,
  `answer1` text NOT NULL,
  `answer2` text NOT NULL,
  `answer3` text NOT NULL,
  `answer4` text NOT NULL,
  `correct_answer` tinyint NOT NULL,
  `course_id` int NOT NULL,
  `lecturer_id` int DEFAULT NULL,
  `lecturer_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `course_id` (`course_id`),
  KEY `lecturer_id` (`lecturer_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `questions_ibfk_2` FOREIGN KEY (`lecturer_id`) REFERENCES `users` (`id`),
  CONSTRAINT `questions_chk_1` CHECK ((`correct_answer` between 1 and 4))
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (2,'dsadsadsa','dsadsa','dsadsa','dsa','dsadsa',1,2,2,'Jane Smith'),(3,'helllooo','answer11','answer22','answer33','answer44',1,4,2,'Jane Smith'),(4,'heeeeee','2131231','sdadwa','xz ','    cxzcxz',4,10,2,'Jane Smith'),(5,'heleleleolpwe','aaa','bbb','ccc','ddd',3,8,2,'Jane Smith'),(6,'dsadsa','dsadsa','dsadsa','dsadsa','dsadsa',2,8,2,'Jane Smith'),(7,'dsadsa','dsa','dsa','dsa','dsa',2,4,2,'Jane Smith'),(8,'dsadsa','dsadsa','dsadsa','dsa','dasdsadsa',4,1,2,'Jane Smith'),(9,'test','a','b','c','d',3,12,2,'Jane Smith'),(10,'test2','aaaaaa','bbbbb','cccc','ddddd',2,15,2,'Jane Smith'),(11,'ttttt','aadsads','adsacxv','432432','342432',3,12,2,'Jane Smith'),(12,'2+2=?','1','2','3','4',4,2,2,'Jane Smith'),(13,'dsadsa','dsadas','dsadsa','adssd','asdads',2,4,2,'Jane Smith'),(14,'helllllooo','1','2','3','4',3,12,2,'Jane Smith');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requests` (
  `examID` varchar(250) NOT NULL,
  `requestID` int NOT NULL AUTO_INCREMENT,
  `RequestedBy` varchar(45) DEFAULT NULL,
  `Reason` varchar(45) DEFAULT NULL,
  `ExtraTime` int DEFAULT NULL,
  PRIMARY KEY (`requestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_inexam`
--

DROP TABLE IF EXISTS `student_inexam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_inexam` (
  `student_id` int NOT NULL,
  `exam_id` int NOT NULL,
  `submitted` int DEFAULT NULL,
  PRIMARY KEY (`student_id`,`exam_id`),
  KEY `exam_id` (`exam_id`),
  CONSTRAINT `student_inexam_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`),
  CONSTRAINT `student_inexam_ibfk_2` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_inexam`
--

LOCK TABLES `student_inexam` WRITE;
/*!40000 ALTER TABLE `student_inexam` DISABLE KEYS */;
INSERT INTO `student_inexam` VALUES (1,7,1),(1,8,1);
/*!40000 ALTER TABLE `student_inexam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_answers`
--

DROP TABLE IF EXISTS `students_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students_answers` (
  `studentId` int NOT NULL,
  `examId` int NOT NULL,
  `courseName` varchar(255) DEFAULT NULL,
  `quesId` int NOT NULL,
  `answerNum` int DEFAULT NULL,
  `correctAnswer` int DEFAULT NULL,
  PRIMARY KEY (`studentId`,`examId`,`quesId`),
  KEY `quesId` (`quesId`),
  KEY `examId` (`examId`),
  CONSTRAINT `students_answers_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `users` (`id`),
  CONSTRAINT `students_answers_ibfk_2` FOREIGN KEY (`quesId`) REFERENCES `questions` (`question_id`),
  CONSTRAINT `students_answers_ibfk_3` FOREIGN KEY (`examId`) REFERENCES `exams` (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_answers`
--

LOCK TABLES `students_answers` WRITE;
/*!40000 ALTER TABLE `students_answers` DISABLE KEYS */;
INSERT INTO `students_answers` VALUES (1,8,'Calculus 1',8,1,4);
/*!40000 ALTER TABLE `students_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjects` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) NOT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'Math'),(2,'Physics'),(3,'Chemistry'),(4,'Biology'),(5,'History'),(6,'English'),(7,'Computer Science');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `isLogged` int NOT NULL DEFAULT '0',
  `role` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'John','Doe','jdo','123',1,0),(2,'Jane','Smith','jsm','456',0,1),(3,'Tom','Johnson','tjo','789',0,2),(4,'Sue','Davis','sda','321',0,0),(5,'Bob','Miller','bmi','654',1,1),(6,'Alice','Brown','abr','987',0,2),(7,'James','Taylor','jta','213',0,0),(8,'Emma','Thomas','eth','546',0,1),(9,'David','White','dwh','879',0,2),(10,'Sophia','Harris','sha','132',0,0),(11,'Daniel','Clark','dcl','465',0,1),(12,'Grace','Rodriguez','gro','798',0,2),(13,'Jack','Lewis','jle','312',0,0),(14,'Ella','Walker','ewa','564',0,1),(15,'Michael','Hall','mha','897',0,2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-18 21:40:34
