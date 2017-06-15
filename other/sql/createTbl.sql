CREATE TABLE messages (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     message CHAR(100) NOT NULL,
     created DATETIME DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id)
);
INSERT INTO messages (message) VALUES ('test msg');