DROP
DATABASE IF EXISTS fundamentals_bookstore_2022;
CREATE
DATABASE IF NOT EXISTS fundamentals_bookstore_2022;
USE fundamentals_bookstore_2022;

CREATE TABLE `fundamentals_bookstore_2022`.`users`
(
    `id`         BIGINT(20) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(30)  NOT NULL,
    `last_name`  VARCHAR(30)  NOT NULL,
    `age`        INT(11) NULL,
    `phone`      VARCHAR(45) NULL,
    `username`   VARCHAR(30)  NOT NULL,
    `password`   VARCHAR(255) NOT NULL,
    `role`       VARCHAR(10)  NOT NULL DEFAULT 'READER',
    `active`     BIT(1) NULL DEFAULT 1,
    `created`    DATETIME NULL DEFAULT NOW(),
    `modified`   DATETIME NULL DEFAULT NOW(),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;
