CREATE SEQUENCE person_seq
  START WITH 1
  INCREMENT BY 1;

CREATE TABLE person (
  id bigint auto_increment,
  name varchar(100),
  birth_date date NOT NULL,
  parent_1_ID number NULL,
  parent_2_ID number NULL,
  partner_ID number NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (parent_1_ID) REFERENCES person(id),
  FOREIGN KEY (parent_2_ID) REFERENCES person(id),
  FOREIGN KEY (partner_ID) REFERENCES person(id)
);

-- Populate table
INSERT into person values (NEXT VALUE FOR person_seq, 'Adam', '1960-01-01', 1, 1, null);

INSERT into person values (NEXT VALUE FOR person_seq, 'Petra Anderson', '1975-01-01', 1, 1, null);
INSERT into person values (NEXT VALUE FOR person_seq, 'Peter Anderson', '1970-01-01', 1, 1, 2);

UPDATE person SET partner_ID = 3 WHERE id = 2;

INSERT into person values (NEXT VALUE FOR person_seq, 'Kwik Anderson', '2015-01-01', 2, 3, null);
INSERT into person values (NEXT VALUE FOR person_seq, 'Kwek Anderson', '2000-01-01', 2, 3, null);
INSERT into person values (NEXT VALUE FOR person_seq, 'Kwak Anderson', '1990-01-01', 2, 3, null);

INSERT into person values (NEXT VALUE FOR person_seq, 'Henk Peters', '1960-12-24', 1, 1, null);
INSERT into person values (NEXT VALUE FOR person_seq, 'Irma Donders', '1980-03-10', 1, 1, null);