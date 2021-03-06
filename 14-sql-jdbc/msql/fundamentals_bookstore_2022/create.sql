DROP
    DATABASE IF EXISTS fundamentals_bookstore_2022;
CREATE
    DATABASE IF NOT EXISTS fundamentals_bookstore_2022;
USE fundamentals_bookstore_2022;

CREATE TABLE `fundamentals_bookstore_2022`.`users`
(
    `id`        BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `firstName` VARCHAR(30)  NOT NULL,
    `lastName`  VARCHAR(30)  NOT NULL,
    `age`       INT(11)      NULL,
    `phone`     VARCHAR(45)  NULL,
    `username`  VARCHAR(30)  NOT NULL,
    `password`  VARCHAR(255) NOT NULL,
    `role`      VARCHAR(10)  NOT NULL DEFAULT 'READER',
    `active`    BIT(1)       NULL     DEFAULT 1,
    `created`   DATETIME     NULL     DEFAULT NOW(),
    `modified`  DATETIME     NULL     DEFAULT NOW(),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8
  COLLATE = utf8_unicode_ci;

INSERT INTO `fundamentals_bookstore_2022`.`users` (`firstName`, `lastName`, `age`, `username`, `password`, `role`, `active`)
VALUES ('Deafult', 'Admin', '1', 'admin', 'Admin123#', 'ADMIN', b'1');
INSERT INTO `fundamentals_bookstore_2022`.`users` (`firstName`, `lastName`, `age`, `username`, `password`, `role`, `active`)
VALUES ('Default', 'Author', '1', 'author', 'Author123#', 'AUTHOR', b'1');
INSERT INTO `fundamentals_bookstore_2022`.`users` (`firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`)
VALUES ('John', 'Doe', '45', '+(1) 43534543469', 'john', 'John123#', 'ADMIN', b'1');
