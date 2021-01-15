CREATE SCHEMA 'taxi_service' DEFAULT CHARACTER SET utf-8 ;
CREATE TABLE `taxi_service`.`manufacturer` (
                                               `manufacturer_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                                               `manufacturer_name` VARCHAR(45) NOT NULL,
                                               `manufacturer_country` VARCHAR(45) NULL,
                                               `deleted` TINYINT NULL DEFAULT 0,
                                               PRIMARY KEY (`manufacturer_id`));
