DROP TABLE IF EXISTS TIMESHEET_ENTRY;
DROP TABLE IF EXISTS TIMESHEET;
DROP TABLE IF EXISTS ENTRY_KIND;


CREATE TABLE ENTRY_KIND (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  DESCRIPTION VARCHAR(50) NOT NULL,
);


INSERT INTO ENTRY_KIND(DESCRIPTION) VALUES('WORK');
INSERT INTO ENTRY_KIND(DESCRIPTION) VALUES('LEAVE_OF_ABSENCE');
INSERT INTO ENTRY_KIND(DESCRIPTION) VALUES('ILL');
INSERT INTO ENTRY_KIND(DESCRIPTION) VALUES('TRAINING');
INSERT INTO ENTRY_KIND(DESCRIPTION) VALUES('OVERTIME');
INSERT INTO ENTRY_KIND(DESCRIPTION) VALUES('OTHERS');

INSERT INTO USER(ID, firstname, lastname, active, emailadress, password, street, housenumber, zipcode, city, phonenumber, accountnumber, firstlogin) VALUES(1, 'test', 'test', true,'hallo@hallo.com', 'hallo', null, null, null, null, 0000000000, null, true);
INSERT INTO USER(ID, firstname, lastname, active, emailadress, password, street, housenumber, zipcode, city, phonenumber, accountnumber, firstlogin) VALUES(2, 'Admin', 'Qien', true,'admin@qien.nl', 'Admin01', null, null, null, null, 0000000000, null, true);