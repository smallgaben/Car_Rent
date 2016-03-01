--Script for H2 DB
--To prevent mistakes

DROP TABLE Checks;
DROP TABLE Orders;
DROP TABLE Users;
DROP TABLE Roles;
DROP TABLE Cars;
DROP TABLE Statuses;
DROP TABLE Classes;

--Creating DB Entities

CREATE TABLE Statuses (
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR (10) NOT NULL UNIQUE
);

INSERT INTO Statuses VALUES (0,'enable');
INSERT INTO Statuses VALUES (1,'disable');
INSERT INTO Statuses VALUES (2,'rent');
INSERT INTO Statuses VALUES (3,'repair');

CREATE TABLE Roles(
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR (10) NOT NULL UNIQUE
);

INSERT INTO Roles VALUES (0,'ADMIN');
INSERT INTO Roles VALUES (1,'MANAGER');
INSERT INTO Roles VALUES (2,'USER');

CREATE TABLE Classes (
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR (10) NOT NULL UNIQUE
);

INSERT INTO Classes VALUES (0,'A');
INSERT INTO Classes VALUES (1,'B');
INSERT INTO Classes VALUES (2,'C');
INSERT INTO Classes VALUES (3,'D');
INSERT INTO Classes VALUES (5,'F');
INSERT INTO Classes VALUES (6,'M');
INSERT INTO Classes VALUES (7,'S');
INSERT INTO Classes VALUES (8,'J');
INSERT INTO Classes VALUES (9,'PICK-UP');

CREATE TABLE Cars (
  id INTEGER NOT NULL auto_increment PRIMARY KEY,
  mark VARCHAR (10) NOT NULL,
  name VARCHAR (20) NOT NULL,
  cost INTEGER NOT NULL,
  class INTEGER NOT NULL REFERENCES Classes(id),
  status INTEGER NOT NULL REFERENCES Statuses
);

INSERT INTO Cars(mark,name,cost,class,status) VALUES ('Audi', 'A3', 1000, 3, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Audi', 'A4', 1200, 3, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Audi', 'TT', 2000, 2, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Audi', 'A5', 2100, 5, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Audi', 'R8', 4000, 7, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('BMW', 'F30/31', 900, 3, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('BMW', 'F22', 1200, 3, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('BMW', 'F36', 1400, 5, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('BMW', 'X6', 3000, 8, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('BMW', 'Grand Tourer', 2100, 1, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('BMW', 'X5', 2800, 8, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Toyota', 'Corolla', 900, 3, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Toyota', 'Prius', 750, 6, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Toyota', 'Land Cruiser', 2800, 8, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Toyota', 'Hilux', 1500, 9, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Toyota', 'Auris', 800, 0, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Nissan', 'Micra', 800, 0, 0);
INSERT INTO Cars (mark,name,cost,class,status) VALUES('Nissan', 'GT-R', 3000, 2, 0);

CREATE TABLE Users(
  id INTEGER NOT NULL auto_increment PRIMARY KEY,
  username VARCHAR (20) NOT NULL UNIQUE ,
  password VARCHAR (50) NOT NULL,
  firstname VARCHAR (30) NOT NULL,
  lastname VARCHAR (30) NOT NULL,
  role INTEGER NOT NULL REFERENCES Roles(id)
);

INSERT INTO Users(username,password,firstname, lastname,role) VALUES ('ADMIN', 'ADMIN', 'Prutkov', 'Miroslav', 0);
INSERT INTO Users(username,password,firstname, lastname,role) VALUES ('smallgaben', 'pfdnhfr040', 'Prut', 'Mirik', 1);
INSERT INTO Users(username,password,firstname, lastname,role) VALUES ('plotnikov', 'plotnikov', 'Plotnikov', 'Denis', 2);

CREATE TABLE Orders(
  id INTEGER NOT NULL auto_increment PRIMARY KEY,
  passport VARCHAR (12) NOT NULL,
  user_id INTEGER NOT NULL REFERENCES Users(id),
  car INTEGER NOT NULL REFERENCES Cars(id),
  startdate DATE NOT NULL,
  finishdate DATE NOT NULL,
  status INTEGER NOT NULL REFERENCES Statuses(id)
);


CREATE TABLE Checks(
  id INTEGER NOT NULL auto_increment PRIMARY KEY,
  date DATE NOT NULL DEFAULT CURRENT_DATE,
  desription VARCHAR (300),
  order_id INTEGER NOT NULL REFERENCES Orders(id)
);

