CREATE TABLE `clients` (
                           `id` INT NOT NULL AUTO_INCREMENT,
                           `client_id` VARCHAR(150) NOT NULL,
                           `client_secret` VARCHAR(400) NOT NULL,
                           `redirect_uri` VARCHAR(500) NOT NULL,
                           `scope` VARCHAR(59) NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE INDEX `clientId` (`client_id`)
)
    COLLATE='utf8mb4_0900_ai_ci'
;
