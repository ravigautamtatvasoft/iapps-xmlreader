-- liquibase formatted sql
-- changeset ravi:1 runOnChange:true endDelimiter:#
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- precondition-sql-check expectedResult:0 select count(*) FROM information_schema.tables where table_schema = DATABASE() AND table_name = "epaper";

CREATE TABLE `epaper` (
                          `id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                          `newspaper_name` VARCHAR(255),
                          `width` BIGINT,
                          `height` BIGINT,
                          `dpi` BIGINT,
                          `uploaded_at` DATETIME,
                          `file_name` VARCHAR(255)
);
#