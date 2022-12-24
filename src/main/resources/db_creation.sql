-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema webAppDb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema webAppDb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `webAppDb` DEFAULT CHARACTER SET utf8 ;
USE `webAppDb` ;

-- -----------------------------------------------------
-- Table `webAppDb`.`ship`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`ship` ;

CREATE TABLE IF NOT EXISTS `webAppDb`.`ship` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `capacity` INT NOT NULL,
  `visited_ports` INT NOT NULL,
  `staff` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`role` ;

CREATE TABLE IF NOT EXISTS `webAppDb`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`user` ;

CREATE TABLE IF NOT EXISTS `webAppDb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `Role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_User_Role_idx` (`Role_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role`
    FOREIGN KEY (`Role_id`)
    REFERENCES `webAppDb`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`route`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`route` ;

CREATE TABLE IF NOT EXISTS `webAppDb`.`route` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `start_of_cruise` DATETIME NOT NULL,
  `end_of_cruise` DATETIME NOT NULL,
  `ship_idShip` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_route_ship1_idx` (`ship_idShip` ASC) VISIBLE,
  CONSTRAINT `fk_route_ship1`
    FOREIGN KEY (`ship_idShip`)
    REFERENCES `webAppDb`.`ship` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`status` ;

CREATE TABLE IF NOT EXISTS `webAppDb`.`status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`ticket` ;

CREATE TABLE IF NOT EXISTS `webAppDb`.`ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `passengers_count` INT NOT NULL,
  `price` INT NOT NULL,
  `user_id` INT NOT NULL,
  `route_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `route_id`, `status_id`),
  INDEX `fk_ticket_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_ticket_route1_idx` (`route_id` ASC) VISIBLE,
  INDEX `fk_ticket_status1_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `fk_ticket_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `webAppDb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `webAppDb`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `webAppDb`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`port`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`port` ;

CREATE TABLE IF NOT EXISTS `webAppDb`.`port` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webAppDb`.`route_has_port`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webAppDb`.`route_has_port` ;

CREATE TABLE IF NOT EXISTS `webAppDb`.`route_has_port` (
  `route_id` INT NOT NULL,
  `port_id` INT NOT NULL,
  PRIMARY KEY (`route_id`, `port_id`),
  INDEX `fk_route_has_port_port1_idx` (`port_id` ASC) VISIBLE,
  INDEX `fk_route_has_port_route1_idx` (`route_id` ASC) VISIBLE,
  CONSTRAINT `fk_route_has_port_route1`
    FOREIGN KEY (`route_id`)
    REFERENCES `webAppDb`.`route` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_route_has_port_port1`
    FOREIGN KEY (`port_id`)
    REFERENCES `webAppDb`.`port` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
