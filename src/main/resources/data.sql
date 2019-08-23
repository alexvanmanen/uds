DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

INSERT INTO billionaires (first_name, last_name, career) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');

-- INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME) VALUES ( 1, 'Alex', 'van Manen' );
-- INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME) VALUES ( 2, 'Paul', 'Veen' );
-- INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME) VALUES ( 3, 'Jan', 'KLaassen' );
INSERT INTO USER(ID, email, password) VALUES
                                             (1,'hallo@hallo.com', 'hallo');
--INSERT INTO USER(ID, email, password) VALUES ( 2,'bart@bartsmit.nl','sinterklaas');