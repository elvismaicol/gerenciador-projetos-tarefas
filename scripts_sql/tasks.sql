USE appmanager;

CREATE TABLE `tasks` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `idProject` int(11) NOT NULL,
 `name` varchar(50) NOT NULL,
 `description` varchar(255) DEFAULT NULL,
 `completed` tinyint(1) NOT NULL,
 `notes` varchar(255) DEFAULT NULL,
 `deadline` date NOT NULL,
 `createdAt` datetime NOT NULL,
 `updatedAt` datetime NOT NULL,
 PRIMARY KEY (`id`),
 KEY `fk_projects` (`idProject`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4
