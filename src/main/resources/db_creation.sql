-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema webAppDb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema webAppDb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `webAppDb` DEFAULT CHARACTER SET utf8;
USE `webAppDb`;

-- -----------------------------------------------------
-- Table `webAppDb`.`route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`route`;

CREATE TABLE IF NOT EXISTS `webAppDb`.`route`
(
    `id`              INT            NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(255)   NOT NULL,
    `start_of_cruise` DATE           NULL,
    `end_of_cruise`   DATE           NULL,
    `duration`        INT            NULL,
    `price`           DECIMAL(10, 2) NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`ship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`ship`;

CREATE TABLE IF NOT EXISTS `webAppDb`.`ship`
(
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(255) NOT NULL,
    `capacity`      INT          NOT NULL,
    `visited_ports` INT          NOT NULL,
    `staff`         INT          NOT NULL,
    `image_path`    VARCHAR(255) NULL,
    `route_id`      INT          NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_ship_route1_idx` (`route_id` ASC) VISIBLE,
    CONSTRAINT `fk_ship_route1`
        FOREIGN KEY (`route_id`)
            REFERENCES `webAppDb`.`route` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`role`;

CREATE TABLE IF NOT EXISTS `webAppDb`.`role`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`user`;

CREATE TABLE IF NOT EXISTS `webAppDb`.`user`
(
    `id`         INT            NOT NULL AUTO_INCREMENT,
    `login`      VARCHAR(45)    NOT NULL,
    `email`      VARCHAR(255)   NOT NULL,
    `password`   VARCHAR(45)    NOT NULL,
    `first_name` VARCHAR(45)    NOT NULL,
    `last_name`  VARCHAR(45)    NOT NULL,
    `balance`    DECIMAL(10, 2) NULL DEFAULT 0,
    `Role_id`    INT            NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_User_Role_idx` (`Role_id` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    CONSTRAINT `fk_User_Role`
        FOREIGN KEY (`Role_id`)
            REFERENCES `webAppDb`.`role` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`status`;

CREATE TABLE IF NOT EXISTS `webAppDb`.`status`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`ticket`;

CREATE TABLE IF NOT EXISTS `webAppDb`.`ticket`
(
    `id`               INT            NOT NULL AUTO_INCREMENT,
    `passengers_count` INT            NOT NULL,
    `price`            DECIMAL(10, 2) NOT NULL,
    `document_path`    VARCHAR(255)   NULL,
    `status_id`        INT            NOT NULL DEFAULT 1,
    `user_id`          INT            NOT NULL,
    `ship_id`          INT            NOT NULL,
    PRIMARY KEY (`id`, `status_id`, `user_id`, `ship_id`),
    INDEX `fk_ticket_user1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_ticket_status1_idx` (`status_id` ASC) VISIBLE,
    INDEX `fk_ticket_ship1_idx` (`ship_id` ASC) VISIBLE,
    CONSTRAINT `fk_ticket_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `webAppDb`.`user` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_ticket_status1`
        FOREIGN KEY (`status_id`)
            REFERENCES `webAppDb`.`status` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_ticket_ship1`
        FOREIGN KEY (`ship_id`)
            REFERENCES `webAppDb`.`ship` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`port`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`port`;

CREATE TABLE IF NOT EXISTS `webAppDb`.`port`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`route_has_port`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`route_has_port`;

CREATE TABLE IF NOT EXISTS `webAppDb`.`route_has_port`
(
    `route_id`       INT  NOT NULL,
    `port_id`        INT  NOT NULL,
    `arrive_time`    DATE NULL,
    `departure_time` DATE NULL,
    PRIMARY KEY (`route_id`, `port_id`),
    INDEX `fk_route_has_port_port1_idx` (`port_id` ASC) VISIBLE,
    INDEX `fk_route_has_port_route1_idx` (`route_id` ASC) VISIBLE,
    CONSTRAINT `fk_route_has_port_route1`
        FOREIGN KEY (`route_id`)
            REFERENCES `webAppDb`.`route` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_route_has_port_port1`
        FOREIGN KEY (`port_id`)
            REFERENCES `webAppDb`.`port` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;

USE `webAppDb`;

DELIMITER $$

USE `webAppDb`$$
DROP TRIGGER IF EXISTS `webAppDb`.`route_BEFORE_INSERT` $$
USE `webAppDb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `webAppDb`.`route_BEFORE_INSERT`
    BEFORE INSERT
    ON `route`
    FOR EACH ROW
BEGIN
    SET NEW.duration = TIMESTAMPDIFF(DAY, NEW.start_of_cruise, NEW.end_of_cruise);
END$$


USE `webAppDb`$$
DROP TRIGGER IF EXISTS `webAppDb`.`route_BEFORE_UPDATE` $$
USE `webAppDb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `webAppDb`.`route_BEFORE_UPDATE`
    BEFORE UPDATE
    ON `route`
    FOR EACH ROW
BEGIN
    SET NEW.duration = TIMESTAMPDIFF(DAY, NEW.start_of_cruise, NEW.end_of_cruise);
END$$


DELIMITER ;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
