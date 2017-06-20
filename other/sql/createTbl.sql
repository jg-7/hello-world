DROP TABLE message;
CREATE TABLE message (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     msg_text CHAR(100) NOT NULL,
     rpl_text CHAR(100),
     username CHAR(50) NOT NULL,
     created DATETIME DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id)
);
INSERT INTO message (msg_text, username) VALUES ('test msg', 'tester');