DROP TABLE IF EXISTS news_record;
CREATE TABLE news_record (
  id varchar(50) NOT NULL,
  title varchar(255) DEFAULT NULL,
  description longtext NOT NULL,
  pub_date varchar(50) NOT NULL,
  img_url varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TRIGGER IF EXISTS news_before_insert;

DELIMITER $$
CREATE TRIGGER news_before_insert BEFORE INSERT ON news_record FOR EACH ROW 
BEGIN
IF new.id is NULL THEN
		SET new.id = REPLACE(UUID(),'-','');
END IF; 
END;$$
DELIMITER ;