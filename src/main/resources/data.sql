DROP TABLE IF EXISTS driver;

CREATE TABLE driver (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
);

INSERT INTO driver (name) VALUES ('Patrick');
INSERT INTO driver (name) VALUES ('John');
INSERT INTO driver (name) VALUES ('Peter');