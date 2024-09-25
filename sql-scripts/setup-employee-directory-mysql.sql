USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;
DROP TABLE IF EXISTS `employee`;

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt (visit site to BCrypt
-- password https://www.bcryptcalculator.com/)
--
-- Default passwords here are: password123
--

INSERT INTO `members`
VALUES
('priya','{bcrypt}$2a$10$IYvbEbHbgVSsie8.KfWpNukJoyCwuiZOGFxu.oE3vNoKWJDT.Kg6a',1),
('shilpa','{bcrypt}$2a$10$IYvbEbHbgVSsie8.KfWpNukJoyCwuiZOGFxu.oE3vNoKWJDT.Kg6a',1),
('max','{bcrypt}$2a$10$IYvbEbHbgVSsie8.KfWpNukJoyCwuiZOGFxu.oE3vNoKWJDT.Kg6a',1);


--
-- Create table `roles`
--

CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
('priya','ROLE_EMPLOYEE'),
('shilpa','ROLE_EMPLOYEE'),
('shilpa','ROLE_MANAGER'),
('max','ROLE_EMPLOYEE'),
('max','ROLE_MANAGER'),
('max','ROLE_ADMIN');

--
-- Create table `roles`
--

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Insert records `employee`
--

INSERT INTO `employee` VALUES
	(1,'Harshada','Patil','harshada@gmail.com'),
	(2,'Anna','Anna','anna@gmail.com'),
	(3,'Sudesh','Gupta','sudesh@gmail.com'),
	(4,'John','John','John@yahoo.com'),
	(5,'Harry','Potter','Harry@gmail.com');