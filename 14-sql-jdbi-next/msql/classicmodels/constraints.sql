ALTER TABLE `classicmodels`.`orderdetails`
    DROP FOREIGN KEY `orderdetails_ibfk_1`;
ALTER TABLE `classicmodels`.`orderdetails`
    ADD CONSTRAINT `orderdetails_ibfk_1`
        FOREIGN KEY (`orderNumber`)
            REFERENCES `classicmodels`.`orders` (`orderNumber`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;
