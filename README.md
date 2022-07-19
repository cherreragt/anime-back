-- Volcando estructura de base de datos para animefl_library
CREATE DATABASE IF NOT EXISTS `animefl_library` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `animefl_library`;

-- Volcando estructura para tabla animefl_library.anime
CREATE TABLE IF NOT EXISTS `anime` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) NOT NULL DEFAULT '',
  `description` varchar(1000) NOT NULL DEFAULT '',
  `picture` varchar(500) NOT NULL DEFAULT '',
  `cover_picture` varchar(500) NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla animefl_library.anime: ~0 rows (aproximadamente)

-- Volcando estructura para tabla animefl_library.anime_category
CREATE TABLE IF NOT EXISTS `anime_category` (
  `anime_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  KEY `FK_anime_category_category` (`category_id`),
  KEY `FK_anime_category_anime` (`anime_id`),
  CONSTRAINT `FK_anime_category_anime` FOREIGN KEY (`anime_id`) REFERENCES `anime` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_anime_category_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='relacion entre category y anime';

-- Volcando datos para la tabla animefl_library.anime_category: ~0 rows (aproximadamente)

-- Volcando estructura para tabla animefl_library.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) NOT NULL,
  `nsfw` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='categorias de anime';

-- Volcando datos para la tabla animefl_library.category: ~0 rows (aproximadamente)

-- Volcando estructura para tabla animefl_library.news
CREATE TABLE IF NOT EXISTS `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) NOT NULL,
  `title` varchar(125) NOT NULL,
  `url` varchar(500) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `image` varchar(500) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='novedades publicadas por usuarios';

-- Volcando datos para la tabla animefl_library.news: ~2 rows (aproximadamente)
INSERT INTO `news` (`id`, `author_id`, `title`, `url`, `description`, `image`, `created_at`) VALUES
	(1, 1, 'Los comienzos de un pijudo', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj7_nmAUxVljtPkn9UkMXmvyPwtCa6i2IsXQ&usqp=CAU', 'Hoy Cristian dividia integers y obtenia 0 dividiendo 10 por 100', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj7_nmAUxVljtPkn9UkMXmvyPwtCa6i2IsXQ&usqp=CAU', '2022-07-13 23:35:32'),
	(2, 1, 'Los pasos de un pijudo alfa', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj7_nmAUxVljtPkn9UkMXmvyPwtCa6i2IsXQ&usqp=CAU', 'Hud ignora los tickets de soporte, le voy a pegar una culeada', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj7_nmAUxVljtPkn9UkMXmvyPwtCa6i2IsXQ&usqp=CAU', '2022-07-14 00:21:08');

-- Volcando estructura para tabla animefl_library.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla animefl_library.roles: ~3 rows (aproximadamente)
INSERT INTO `roles` (`id`, `name`) VALUES
	(2, 'ROLE_ADMIN'),
	(1, 'ROLE_ROOT'),
	(3, 'ROLE_USER');

-- Volcando estructura para tabla animefl_library.social_network
CREATE TABLE IF NOT EXISTS `social_network` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(125) NOT NULL,
  `icon` varchar(500) NOT NULL,
  `active` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla animefl_library.social_network: ~3 rows (aproximadamente)
INSERT INTO `social_network` (`id`, `name`, `icon`, `active`) VALUES
	(1, 'Facebook', 'https://flyclipart.com/black-facebook-icon-facebook-icon-png-744186', 1),
	(2, 'Instagram', 'https://flyclipart.com/instagram-logo-icon-instagram-gif-transparent-png-new-instagram-logo-png-83657', 1),
	(3, 'Reddit', 'https://flyclipart.com/reddit-reddit-logo-alien-png-vector-free-download-reddit-logo-png-231835', 0);

-- Volcando estructura para tabla animefl_library.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(125) NOT NULL,
  `username` varchar(125) NOT NULL,
  `password` varchar(125) NOT NULL,
  `token` varchar(500) NOT NULL,
  `register_date` datetime NOT NULL,
  `sex` varchar(125) NOT NULL,
  `birthday` date NOT NULL,
  `about_me` varchar(225) NOT NULL,
  `avatar` varchar(225) NOT NULL,
  `active` bit(7) NOT NULL,
  `wallpaper` varchar(125) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla animefl_library.users: ~5 rows (aproximadamente)
INSERT INTO `users` (`id`, `email`, `username`, `password`, `token`, `register_date`, `sex`, `birthday`, `about_me`, `avatar`, `active`, `wallpaper`) VALUES
	(1, 'pijudo@hot.com', 'pijudo', 'pijalarga', 'test', '2022-07-13 03:51:16', 'masculino', '2022-07-13', 'busco novia', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj7_nmAUxVljtPkn9UkMXmvyPwtCa6i2IsXQ&usqp=CAU', b'0000000', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj7_nmAUxVljtPkn9UkMXmvyPwtCa6i2IsXQ&usqp=CAU'),
	(2, 'chris.34@gmail.com', 'username', 'passs', 'tttt1', '2022-07-16 23:22:47', 'sex', '2022-07-16', 'me', 'avatar', b'0000001', 'wp'),
	(3, 'chris.3414@gmail.com', '3414', 'passs', 'tttt1', '2022-07-16 23:24:23', 'sex', '2022-07-16', 'me', 'avatar', b'0000001', 'wp'),
	(4, 'chris.23233@gmail.com', '23233', 'passs', 'tttt1', '2022-07-16 23:25:10', 'sex', '2022-07-16', 'me', 'avatar', b'0000001', 'wp'),
	(5, 'chris.34344@gmail.com', '34344', 'passs', 'tttt1', '2022-07-16 23:26:17', 'sex', '2022-07-16', 'me', 'avatar', b'0000001', 'wp');

-- Volcando estructura para tabla animefl_library.user_has_role
CREATE TABLE IF NOT EXISTS `user_has_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_has_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_has_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla animefl_library.user_has_role: ~5 rows (aproximadamente)
INSERT INTO `user_has_role` (`user_id`, `role_id`) VALUES
	(1, 3),
	(2, 3),
	(3, 3),
	(4, 3),
	(5, 3);

-- Volcando estructura para tabla animefl_library.user_social_network
CREATE TABLE IF NOT EXISTS `user_social_network` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL DEFAULT 0,
  `social_id` bigint(20) NOT NULL DEFAULT 0,
  `link` varchar(500) NOT NULL DEFAULT '',
  `visible` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_social_network_users` (`user_id`),
  KEY `FK_user_social_network_social_network` (`social_id`),
  CONSTRAINT `FK_user_social_network_social_network` FOREIGN KEY (`social_id`) REFERENCES `social_network` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_social_network_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='relacion entre user y social_network';
