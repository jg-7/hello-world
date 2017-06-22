DROP TABLE message;
CREATE TABLE message (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     msg_text CHAR(100) NOT NULL,
     rpl_text CHAR(100),
     username CHAR(50) NOT NULL,
     city CHAR(50) NOT NULL,
     created DATETIME DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id)
);
INSERT INTO message (msg_text, rpl_text, username, city) VALUES ('test msg', 'test rpl', 'tester', 'Toronto');