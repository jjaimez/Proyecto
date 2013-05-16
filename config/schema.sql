DROP TABLE IF EXISTS `inmoapp_development`.`addresses`;
CREATE  TABLE `inmoapp_development`.`addresses` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT ,
  `street` VARCHAR(45) NOT NULL ,
  `num` VARCHAR(45) NOT NULL ,
  `neighborhood` VARCHAR(45) NULL DEFAULT NULL ,
  `city_id` INT NOT NULL ,
  PRIMARY KEY (`id`) );


DROP TABLE IF EXISTS `inmoapp_development`.`cities`;
CREATE  TABLE `inmoapp_development`.`cities` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `code` INT NOT NULL ,
  PRIMARY KEY (`id`) );

DROP TABLE IF EXISTS `inmoapp_development`.`owners`;
CREATE  TABLE `inmoapp_development`.`owners` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT ,
  `first_name` VARCHAR(45) NOT NULL ,
  `last_name` VARCHAR(45) NOT NULL ,
  `dni` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NULL DEFAULT NULL ,
  `address_id` INT  NOT NULL ,
  PRIMARY KEY (`id`, `dni`) );

DROP TABLE IF EXISTS `inmoapp_development`.`real_estates`;
CREATE  TABLE `inmoapp_development`.`real_estates` (
  `id` INT UNIQUE NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `phone_number` VARCHAR(45) NOT NULL ,
  `web_site` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NULL DEFAULT NULL ,
  `address_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `name`) );

DROP TABLE IF EXISTS `inmoapp_development`.`owners_real_estates`;
CREATE  TABLE `inmoapp_development`.`owners_real_estates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `owner_id` INT NULL ,
  `real_estate_id` INT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) );

DROP TABLE IF EXISTS `inmoapp_development`.`buildings`;
CREATE  TABLE `inmoapp_development`.`buildings` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(300) NULL ,
  `price` VARCHAR(45) NULL ,
  `offer` VARCHAR(45) NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `address_id` INT NOT NULL ,
  `owner_id` INT NULL DEFAULT NULL ,
  `real_estate_id` INT NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `address_id_UNIQUE` (`address_id` ASC) );

