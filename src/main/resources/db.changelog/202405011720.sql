DROP TABLE IF EXISTS `epaper`;

CREATE TABLE `epaper` (
                        id BIGINT PRIMARY KEY,
                        newspaper_name VARCHAR(255),
                        width BIGINT,
                        height BIGINT,
                        dpi BIGINT,
                        uploaded_at DATE,
                        file_name VARCHAR(255)
);