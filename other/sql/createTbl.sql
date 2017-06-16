CREATE TABLE message (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     msg_text CHAR(100) NOT NULL,
     created DATETIME DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id)
);
INSERT INTO message (msg_text) VALUES ('test msg');