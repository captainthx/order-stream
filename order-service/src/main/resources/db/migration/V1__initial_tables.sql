CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `mydb`;

CREATE TABLE IF NOT EXISTS `users`
(
    `id`        BIGINT AUTO_INCREMENT PRIMARY KEY,
    `username`  VARCHAR(30),
    `password`  VARCHAR(255),
    `version`   INT          NOT NULL DEFAULT 0,
    `cdt`       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `udt`       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `user_username_uq` UNIQUE (`username`)
) engine = InnoDB;

