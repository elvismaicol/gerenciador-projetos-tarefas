USE appmanager;

CREATE TABLE `projects` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(50) NOT NULL,
 `description` varchar(255) DEFAULT NULL,
 `createdAt` datetime NOT NULL,
 `updateAt` datetime NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4
