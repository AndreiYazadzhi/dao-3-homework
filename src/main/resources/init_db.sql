CREATE SCHEMA 'taxi_service' DEFAULT CHARACTER SET utf-8 ;
CREATE TABLE `taxi_service`.`manufacturer` (
                                               `manufacturer_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                               `manufacturer_name` VARCHAR(45) NOT NULL,
                                               `manufacturer_country` VARCHAR(45) NULL,
                                               `deleted` TINYINT NULL DEFAULT 0,
                                               PRIMARY KEY (`manufacturer_id`));
CREATE TABLE `taxi_service`.`cars` (
                                       `cars_id` INT NOT NULL AUTO_INCREMENT,
                                       `manufacturer_id` BIGINT NULL,
                                       `cars_model` VARCHAR(45) NULL,
                                       `deleted` TINYINT NOT NULL DEFAULT 0,
                                       PRIMARY KEY (`cars_id`),
                                       INDEX `manufacturer_id_idx` (`manufacturer_id` ASC) VISIBLE,
                                       CONSTRAINT `manufacturer_id`
                                           FOREIGN KEY (`manufacturer_id`)
                                               REFERENCES `taxi_service`.`manufacturer` (`manufacturer_id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);

CREATE TABLE `taxi_service`.`drivers` (
                                          `drivers_id` INT NOT NULL AUTO_INCREMENT,
                                          `drivers_name` VARCHAR(45) NOT NULL,
                                          `license_number` VARCHAR(45) NOT NULL,
                                          `deleted` TINYINT NOT NULL DEFAULT 0,
                                          PRIMARY KEY (`drivers_id`),
                                          UNIQUE INDEX `license_number_UNIQUE` (`license_number` ASC) VISIBLE);

CREATE TABLE `taxi_service`.`drivers_cars` (
                                               `driver_id` INT NOT NULL,
                                               `car_id` INT NOT NULL,
                                               INDEX `driver_id_fk_idx` (`driver_id` ASC) VISIBLE,
                                               INDEX `car_id_fk_idx` (`car_id` ASC) VISIBLE,
                                               CONSTRAINT `driver_id_fk`
                                                   FOREIGN KEY (`driver_id`)
                                                       REFERENCES `taxi_service`.`drivers` (`drivers_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `car_id_fk`
                                                   FOREIGN KEY (`car_id`)
                                                       REFERENCES `taxi_service`.`cars` (`cars_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION);
ALTER TABLE `taxi_service`.`drivers_cars`
    ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT AFTER `car_id`,
ADD PRIMARY KEY (`id`);
;
ALTER TABLE `taxi_service`.`drivers`
    ADD COLUMN `login` VARCHAR(45) NOT NULL AFTER `deleted`,
    ADD COLUMN `password` VARCHAR(45) NOT NULL AFTER `login`;

